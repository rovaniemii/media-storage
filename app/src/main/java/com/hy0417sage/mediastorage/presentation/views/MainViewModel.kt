package com.hy0417sage.mediastorage.presentation.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hy0417sage.mediastorage.domain.usecase.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUseCase: GetUseCase,
) : ViewModel() {

    fun thumbnailSearch(subject: String) {
        viewModelScope.launch {
            val quotes = getUseCase.getFlowData(subject)
            quotes.collect { data ->
                Log.d(this.toString(), "검색결과 확인 : $data")
            }
        }
    }
}