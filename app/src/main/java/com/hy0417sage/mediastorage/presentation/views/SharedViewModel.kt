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
    private val _storageDataList = MutableLiveData<List<ViewData>>()
    val storageDataList: LiveData<List<ViewData>> get() = _storageDataList

    fun thumbnailSearch(subject: String) {
        viewModelScope.launch {
            val quotes = getUseCase.getSearchData(subject)
            quotes.collect { data ->
                _searchDataList.value = data
            }
        }
    }

    fun setStorageDataList(){
        _storageDataList.value = sharedPreference.getAllValue().sortedByDescending { it.saveTime }
    }

    fun storageSorted(storageData: ViewData) { //ViewData
        if (sharedPreference.getValue(storageData.thumbnail) == null) {
            sharedPreference.setValue(storageData.thumbnail, storageData.copy(like = true, saveTime = DateUtil.dateAndTime()))
        }
    }
}