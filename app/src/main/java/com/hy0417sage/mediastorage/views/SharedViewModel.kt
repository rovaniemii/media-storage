package com.hy0417sage.mediastorage.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.domain.usecase.SearchImageUseCase
import com.hy0417sage.mediastorage.ApplicationClass.Companion.sharedPreference
import com.hy0417sage.core.util.SimpleDateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val searchImageUseCase: SearchImageUseCase,
) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> get() = _errorMessage

    private val _searchDataList = MutableLiveData<List<SearchItem>>(arrayListOf())
    val searchDataList: LiveData<List<SearchItem>> get() = _searchDataList
    private val _storageDataList = MutableLiveData<List<SearchItem>?>()
    val storageDataList: MutableLiveData<List<SearchItem>?> get() = _storageDataList

    var keyWord: String = ""
    var callPage: Int = 1

    fun thumbnailSearch(subject: String, page: Int = callPage) {
        viewModelScope.launch {
            val quotes = searchImageUseCase.invoke(subject, page)
            if (quotes.isNotEmpty()) {
                _errorMessage.value = ""
                val search = quotes.toMutableList()
                for (i in 0 until search.size) {
                    if (sharedPreference.getValue(search[i].imageUrl) != null) {
                        search[i] = search[i].copy(bookmark = true)
                    }
                }
                if (page == 1) {
                    _searchDataList.value = search
                } else {
                    val view = _searchDataList.value?.toMutableList() ?: mutableListOf()
                    _searchDataList.value = (view + search).sortedByDescending { it.datetime }
                }
                callPage++
            }else{
                if (page == 1){
                    _searchDataList.value = arrayListOf()
                    _errorMessage.value = "결과가 없습니다."
                }
            }
        }
    }

    //화면 첫 실행시 sharedPreference에 저장되어진 데이터를 저장된 순으로 정렬해 storageDataList를 업데이트 해줍니다.
    fun setStorageDataList() {
        _storageDataList.value = sharedPreference.getAllValue().sortedBy { it.bookmarkTime }
    }

    //좋아요 데이터가 변화 되었다면, sharedPreference 에 이를 반영합니다.
    fun updateStorage(searchData: SearchItem) {
        if (sharedPreference.getValue(searchData.imageUrl) == null) {
            sharedPreference.setValue(searchData.imageUrl,
                searchData.copy(bookmark = true, bookmarkTime = SimpleDateUtil.dateAndTime()))
            changeData(searchData, true)
        } else {
            sharedPreference.deleteValue(searchData.imageUrl)
            changeData(searchData, false)
        }
    }

    //좋아요 데이터가 변화 되었다면, fragment 두 화면에서 구독중인 데이터 storageDataList, searchDataList 데이터를 업데이트해줍니다.
    private fun changeData(searchItem: SearchItem, bookmark: Boolean) {
        val storage =
            sharedPreference.getAllValue().sortedBy { it.bookmarkTime }.toMutableList()
        _storageDataList.value = storage
        val search = _searchDataList.value?.toMutableList() ?: mutableListOf()
        val index = search.indexOf(searchItem)
        search[index] = search[index].copy(bookmark = bookmark)
        _searchDataList.value = search
    }
}