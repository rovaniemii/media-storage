package com.hy0417sage.mediastorage.presentation.views

import androidx.lifecycle.ViewModel
import com.hy0417sage.mediastorage.domain.usecase.GetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUseCase: GetUseCase,
) : ViewModel() {

}