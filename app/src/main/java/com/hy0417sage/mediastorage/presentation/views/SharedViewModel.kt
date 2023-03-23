package com.hy0417sage.mediastorage.presentation.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.mediastorage.ApplicationClass
import com.hy0417sage.mediastorage.ApplicationClass.Companion.sharedPreference
import com.hy0417sage.mediastorage.domain.model.ViewData
import com.hy0417sage.mediastorage.domain.usecase.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getUseCase: GetUseCase,
) : ViewModel() {

    private val _searchData = MutableLiveData<List<ViewData>>()
    val searchData: LiveData<List<ViewData>> get() = _searchData
    private val _storageData = MutableLiveData<List<ViewData>>()
    val storageData: LiveData<List<ViewData>> get() = _storageData

    fun thumbnailSearch(subject: String) {
        viewModelScope.launch {
            val quotes = getUseCase.getFlowData(subject)
            quotes.collect { data ->
                var tmp = data as MutableList<ViewData>
                for (i in 0 until tmp.size) {
                    if (sharedPreference.getValue(tmp[i].thumbnail) != null) {
                        Log.d("확인", "${sharedPreference.size + 1}")
                        tmp[i] = tmp[i].copy(like = sharedPreference.size + 1)
                    }
                }
                _searchData.value = tmp
            }
        }
    }

    fun storageDataList() {
        _storageData.value = sharedPreference.getAllValue()
    }
}