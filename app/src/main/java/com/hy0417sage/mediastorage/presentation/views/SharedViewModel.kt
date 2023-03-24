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

    private fun changeData(viewData: ViewData, like: Boolean) {
        val storage =
            sharedPreference.getAllValue()?.sortedBy { it.saveTime }?.toMutableList() ?: mutableListOf()
        _storageDataList.value = storage
        val search = _searchDataList.value?.toMutableList() ?: mutableListOf()
        val index = search.indexOf(viewData)
        search[index] = search[index].copy(like = like)
        _searchDataList.value = search
    }
}