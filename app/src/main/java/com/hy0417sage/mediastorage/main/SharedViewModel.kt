package com.hy0417sage.mediastorage.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hy0417sage.core.data.SearchUiState
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.core.ui.BaseViewModel
import com.hy0417sage.core.util.Event
import com.hy0417sage.domain.usecase.FirstSearchImagesUseCase
import com.hy0417sage.domain.usecase.SearchImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val searchImagesUseCase: SearchImagesUseCase,
    private val firstSearchImagesUseCase: FirstSearchImagesUseCase,
) : BaseViewModel() {

    // 상태(State)는 StateFlow, 이벤트(Event)는 SharedFlow
    // 출처: https://myungpyo.medium.com/stateflow-%EC%99%80-sharedflow-32fdb49f9a32
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _firstPagingData = MutableSharedFlow<PagingData<SearchItem>>()
    val firstPagingData = _firstPagingData.asSharedFlow()

    private val _pagingData = MutableSharedFlow<PagingData<SearchItem>>()
    val pagingData = _pagingData.asSharedFlow()

    private val _imageUrl = MutableSharedFlow<Event<String>>()
    val imageUrl = _imageUrl.asSharedFlow()

    /* 기존 구현에서 데이터를 다 불러오고 시간순으로 정렬하면 로딩 시간이 길어지는 문제가 있었습니다.
       SearchItemAdapter의 DiffUtil 함수가 있기 때문에 일부만 데이터를 불러오고, 유저가 앱을 사용하는 동안
       비동기로 데이터들을 불러오고 자연스럽게 갱신하는것 까지 구현이 가능했습니다. */

    /* 일단 첫번째 페이지만 불러오는 함수 */
    fun firstSearchImages(query: String) = viewModelScope.launch {
        _uiState.update { it.copy(isGuideMessageVisible = false) }
        _uiState.update { it.copy(isLoading = true) }
        _uiState.update { it.copy(currentQuery = query) }
        firstSearchImagesUseCase.invoke(
            query = query
        )
            .cachedIn(viewModelScope).collect {
                _firstPagingData.emit(it)
            }
    }

    /* 앞서 불러온 첫번째 페이지가 유저에게 보여질 동안 전체 이미지를 불러오는 함수 */
    fun searchImages() = viewModelScope.launch {
        searchImagesUseCase.invoke(
            query = _uiState.value.currentQuery
        )
            .cachedIn(viewModelScope).collect {
                _pagingData.emit(it)
            }
    }

    fun setProgressBar(isVisible: Boolean) {
        if (isVisible) {
            _uiState.update { it.copy(isLoading = true) }
        } else {
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun removeImage(imageUrl: String) = viewModelScope.launch {
        _imageUrl.emit(Event(imageUrl))
    }
}