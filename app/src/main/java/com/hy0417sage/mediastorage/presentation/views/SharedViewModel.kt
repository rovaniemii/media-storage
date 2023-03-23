package com.hy0417sage.mediastorage.presentation.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // 검색 결과 리스트
    private val _searchData = MutableLiveData<List<ViewData>>()
    val searchData: LiveData<List<ViewData>> get() = _searchData

    // 보관 리스트
    private val _storageData = MutableLiveData<List<ViewData>>()
    val storageData: LiveData<List<ViewData>> get() = _storageData

    fun thumbnailSearch(subject: String) {
        viewModelScope.launch {
            val quotes = getUseCase.getFlowData(subject)
            quotes.collect { data ->
                val tmp = data as MutableList<ViewData>
                for (i in 0 until tmp.size) {
                    if (sharedPreference.getValue(tmp[i].thumbnail) != null) {
                        tmp[i] = tmp[i].copy(like = true)
                    }
                }
                _searchData.value = tmp
            }
        }
    }

    // 검색 데이터
    fun updateLike() {
        val tmp = searchData.value as MutableList<ViewData>
        for (i in 0 until tmp.size) {
            if (sharedPreference.getValue(tmp[i].thumbnail) != null) {
                tmp[i] = tmp[i].copy(like = true)
            }
        }
        _searchData.value = tmp
    }

    //저장 데이터에 변경 사항이 발생했다.
    fun storageDataList() {
        _storageData.value = sharedPreference.getAllValue()
    }
}