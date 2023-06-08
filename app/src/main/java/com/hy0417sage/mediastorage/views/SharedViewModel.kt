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

    private val _searchItem = MutableLiveData<List<SearchItem>>(arrayListOf())
    val searchItem: LiveData<List<SearchItem>> get() = _searchItem
    private val _bookmarksList = MutableLiveData<List<SearchItem>?>()
    val bookmarksList: MutableLiveData<List<SearchItem>?> get() = _bookmarksList

    var keyWord: String = ""
    var callPage: Int = 1

    fun searchItem(subject: String, page: Int = callPage) {
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
                    _searchItem.value = search
                } else {
                    val view = _searchItem.value?.toMutableList() ?: mutableListOf()
                    _searchItem.value = (view + search).sortedByDescending { it.datetime }
                }
                callPage++
            }else{
                if (page == 1){
                    _searchItem.value = arrayListOf()
                    _errorMessage.value = "결과가 없습니다."
                }
            }
        }
    }

    //화면 첫 실행시 sharedPreference에 저장되어진 데이터를 저장된 순으로 정렬해 storageDataList를 업데이트 해줍니다.
    fun setBookmarks() {
        _bookmarksList.value = sharedPreference.getAllValue().sortedBy { it.bookmarkTime }
    }

    //좋아요 데이터가 변화 되었다면, sharedPreference 에 이를 반영합니다.
    fun updateBookmarks(searchData: SearchItem) {
        if (sharedPreference.getValue(searchData.imageUrl) == null) {
            sharedPreference.setValue(searchData.imageUrl,
                searchData.copy(bookmark = true, bookmarkTime = SimpleDateUtil.dateAndTime()))
            changeBookMarks(searchData, true)
        } else {
            sharedPreference.deleteValue(searchData.imageUrl)
            changeBookMarks(searchData, false)
        }
    }

    //좋아요 데이터가 변화 되었다면, fragment 두 화면에서 구독중인 데이터 storageDataList, searchDataList 데이터를 업데이트해줍니다.
    private fun changeBookMarks(searchItem: SearchItem, bookmark: Boolean) {
        val storage =
            sharedPreference.getAllValue().sortedBy { it.bookmarkTime }.toMutableList()
        _bookmarksList.value = storage
        val search = _searchItem.value?.toMutableList() ?: mutableListOf()
        val index = search.indexOf(searchItem)
        search[index] = search[index].copy(bookmark = bookmark)
        _searchItem.value = search
    }
}