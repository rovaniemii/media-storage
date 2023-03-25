package com.hy0417sage.mediastorage.presentation.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.mediastorage.ApplicationClass.Companion.sharedPreference
import com.hy0417sage.mediastorage.domain.model.ViewData
import com.hy0417sage.mediastorage.domain.usecase.GetUseCase
import com.hy0417sage.mediastorage.presentation.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getUseCase: GetUseCase,
) : ViewModel() {

    private val scroll = MutableLiveData<List<ViewData>>()
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> get() = _errorMessage

    private val _searchDataList = MutableLiveData<List<ViewData>>()
    val searchDataList: LiveData<List<ViewData>> get() = _searchDataList
    private val _storageDataList = MutableLiveData<List<ViewData>?>()
    val storageDataList: MutableLiveData<List<ViewData>?> get() = _storageDataList

    fun thumbnailSearch(subject: String) {
        viewModelScope.launch {
            val quotes = getUseCase.getSearchData(subject)
            quotes.collect { data ->
                if (data.isNotEmpty()) {
                    val search = data as MutableList<ViewData>
                    for (i in 0 until search.size) {
                        if (sharedPreference.getValue(search[i].thumbnail) != null) {
                            search[i] = search[i].copy(like = true)
                        }
                    }
                    _errorMessage.value = ""
                    scroll.value = search
                    _searchDataList.value = search.subList(0, 10)
                } else {
                    _errorMessage.value = "결과가 없습니다."
                    _searchDataList.value = arrayListOf()
                }
            }
        }
    }

    //화면 첫 실행시 sharedPreference에 저장되어진 데이터를 저장된 순으로 정렬해 storageDataList를 업데이트 해줍니다.
    fun setStorageDataList() {
        _storageDataList.value = sharedPreference.getAllValue().sortedBy { it.saveTime }
    }

    //좋아요 데이터가 변화 되었다면, sharedPreference 에 이를 반영합니다.
    fun updateStorage(searchData: ViewData) {
        if (sharedPreference.getValue(searchData.thumbnail) == null) {
            sharedPreference.setValue(searchData.thumbnail,
                searchData.copy(like = true, saveTime = DateUtil.dateAndTime()))
            changeData(searchData, true)
        } else {
            sharedPreference.deleteValue(searchData.thumbnail)
            changeData(searchData, false)
        }
    }

    //좋아요 데이터가 변화 되었다면, fragment 두 화면에서 구독중인 데이터 storageDataList, searchDataList 데이터를 업데이트해줍니다.
    private fun changeData(viewData: ViewData, like: Boolean) {
        val storage =
            sharedPreference.getAllValue()?.sortedBy { it.saveTime }?.toMutableList()
                ?: mutableListOf()
        _storageDataList.value = storage
        val search = _searchDataList.value?.toMutableList() ?: mutableListOf()
        val index = search.indexOf(viewData)
        search[index] = search[index].copy(like = like)
        _searchDataList.value = search
    }

    //스크롤을 통해 다음 페이지를 불러옵니다.
    fun scrollLoadData() {
        val currentSize = _searchDataList.value?.size
        val scroll = scroll.value?.toMutableList() ?: mutableListOf()
        val start = currentSize?.plus(1) ?: 0
        val mid = currentSize?.plus(10) ?: 0
        val last = currentSize?.rem(10) ?: 0

        if ((currentSize?.plus(10) ?: 0) > scroll.size) {
            _searchDataList.value =
                _searchDataList.value?.plus(scroll.subList(start, start + last - 2))
        } else {
            _searchDataList.value =
                _searchDataList.value?.plus(scroll.subList(start, mid))
        }
    }
}