package com.hy0417sage.mediastorage.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.core.ui.BaseViewModel
import com.hy0417sage.domain.usecase.FirstSearchImagesUseCase
import com.hy0417sage.domain.usecase.SearchImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class SharedViewModel @Inject constructor(
    private val searchImagesUseCase: SearchImagesUseCase,
    private val firstSearchImagesUseCase: FirstSearchImagesUseCase,
) : BaseViewModel() {
    private val _firstPagingData = MutableSharedFlow<PagingData<SearchItem>>()
    val firstPagingData = _firstPagingData.asSharedFlow()

    private val _pagingData = MutableSharedFlow<PagingData<SearchItem>>()
    val pagingData = _pagingData.asSharedFlow()

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private val manualSearchTrigger = MutableSharedFlow<String>(extraBufferCapacity = 1)

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    init {
        observeSearchQuery()
        observeFirstPagingData()
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            merge(
                searchQuery
                    .debounce(500)
                    .filter { it.trim().isNotBlank() }
                    .distinctUntilChanged(),
                manualSearchTrigger, // debounce 없이 바로 요청
            )
                .flatMapLatest { query ->
                    firstSearchImagesUseCase
                        .invoke(query = query)
                        .cachedIn(viewModelScope)
                }
                .collectLatest {
                    _firstPagingData.emit(it)
                }
        }
    }

    private fun observeFirstPagingData() {
        viewModelScope.launch {
            firstPagingData
                .collectLatest {
                    searchImagesUseCase
                        .invoke(query = searchQuery.value)
                        .cachedIn(viewModelScope)
                        .collect { _pagingData.emit(it) }
                }
        }
    }

    fun onSearchButtonClick() {
        manualSearchTrigger.tryEmit(searchQuery.value)
    }
}