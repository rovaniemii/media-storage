package com.hy0417sage.core.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * BaseViewModel
 */
abstract class BaseViewModel : ViewModel() {

    // LiveData 를 사용하여 ProgressBar 를 On/Off 시킨다.
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _percent = MutableLiveData<String>("0")
    val percent: LiveData<String> get() = _percent

    fun showProgress() {
        _isLoading.value = true
    }

    fun hideProgress() {
        _isLoading.value = false
    }

    fun progressPercent(load: String) {
        _percent.value = load
    }
}