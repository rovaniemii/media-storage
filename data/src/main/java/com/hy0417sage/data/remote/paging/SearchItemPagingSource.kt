package com.hy0417sage.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.core.util.Constants.PAGE_SIZE
import com.hy0417sage.core.util.Constants.SEARCH_STARTING_PAGE_INDEX
import com.hy0417sage.data.remote.model.ApiPagingModel

//https://developer.android.com/codelabs/android-paging-basics?hl=ko#4
class SearchItemPagingSource(
    private val pagingData: ApiPagingModel,
) : PagingSource<Int, SearchItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        pagingData.exception?.run {
            return LoadResult.Error(this)
        }
        val page = params.key ?: SEARCH_STARTING_PAGE_INDEX
        return try {
            val chunkedList = fetchImagesByPage(
                data = pagingData,
                page = page
            )
            LoadResult.Page(
                data = chunkedList,
                prevKey = if (page == SEARCH_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == (pagingData.searchItemList.size / PAGE_SIZE) + 1) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun fetchImagesByPage(data: ApiPagingModel, page: Int): List<SearchItem> {
        val chunkedList = data.searchItemList.chunked(PAGE_SIZE)
        return chunkedList[page - 1]
    }
}