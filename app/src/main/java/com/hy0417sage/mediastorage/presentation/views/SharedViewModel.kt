package com.hy0417sage.mediastorage.presentation.views

import android.util.Log
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

    private val _searchDataList = MutableLiveData<List<ViewData>>()
    val searchDataList: LiveData<List<ViewData>> get() = _searchDataList
    private val _storageDataList = MutableLiveData<List<ViewData>?>()
    val storageDataList: MutableLiveData<List<ViewData>?> get() = _storageDataList

    fun thumbnailSearch(subject: String) {
        viewModelScope.launch {
            val quotes = getUseCase.getSearchData(subject)
            quotes.collect { data ->
                val search = data as MutableList<ViewData>
                for (i in 0 until search.size) {
                    if (sharedPreference.getValue(search[i].thumbnail) != null) {
                        search[i] = search[i].copy(like = true)
                    }
                }
                _searchDataList.value = search
                _searchDataList.postValue(search)
            }
        }
    }

    fun setStorageDataList() {
        _storageDataList.value = sharedPreference.getAllValue().sortedBy { it.saveTime }
    }

    // 데이터 저장시 동작
    fun addStorage(searchData: ViewData) {
        if (sharedPreference.getValue(searchData.thumbnail) == null) {
            sharedPreference.setValue(searchData.thumbnail,
                searchData.copy(like = true, saveTime = DateUtil.dateAndTime()))

            val storage =
                sharedPreference.getAllValue()?.sortedBy { it.saveTime } as MutableList<ViewData>?
            _storageDataList.value = storage

            val search = _searchDataList.value as MutableList<ViewData>
            val index = search.indexOf(searchData)
            search[index] = search[index].copy(like = true)
            Log.d("로그", "삽입 데이터 반영 되었나? ${search[index]}")
            _searchDataList.value = search
        }else{
            deleteStorageData(searchData)
        }
    }

    //TODO 코드 리팩토링!
    fun deleteStorageData(storageData: ViewData) {
        if (sharedPreference.getValue(storageData.thumbnail) != null) {
            sharedPreference.deleteValue(storageData.thumbnail)
            val storage = sharedPreference.getAllValue()?.sortedBy { it.saveTime } as MutableList<ViewData>?
            _storageDataList.value = storage

            val search = _searchDataList.value as MutableList<ViewData>
            val index = search.indexOf(storageData)
            search[index] = search[index].copy(like = false)
            Log.d("로그", "삭제 데이터 반영 되었나? ${search[index]}")
            _searchDataList.value = search
        }
    }
}