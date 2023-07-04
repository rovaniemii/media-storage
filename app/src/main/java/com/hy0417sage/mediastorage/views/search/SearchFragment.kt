package com.hy0417sage.mediastorage.views.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.core.ui.BaseFragment
import com.hy0417sage.core.util.addImageToList
import com.hy0417sage.core.util.removeImageFromList
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.databinding.FragmentSearchBinding
import com.hy0417sage.mediastorage.views.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException

/*
* 첫 번째 fragment : 검색 결과 화면
* */
@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SharedViewModel by activityViewModels()

    private val adapter = SearchItemAdapter(
        onClick = {
            activity?.run {
                /* Fragment가 attached된 Activity가 finishing 되는 상황이면 return */
                if (isFinishing)
                    return@run

                if (it.bookmark) {
                    removeImageFromList(it)
                } else {
                    addImageToList(it)
                }
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRecycler.layoutManager = LinearLayoutManager(activity)
        binding.rvRecycler.adapter = adapter
        setSearchView()
        setUiStateObserver()
        setSearchItemFlowObserver()
    }

    private fun setSearchView(){
        /* searchView(검색 UI) Setting */
        binding.svSearch.setIconifiedByDefault(false)
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.run {
                    viewModel.firstSearchImages(this)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setUiStateObserver(){
        /* UIState Observer */
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    /* ProgressBar */
                    if (it.isLoading) {
                        showProgressBar()
                    } else {
                        hideProgressBar()
                    }
                }
            }
        }

        /* 페이징 Error handling */
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collectLatest {
                    when(val currentState = it.refresh){
                        is LoadState.Error -> {
                            when(currentState.error){
                                is UnknownHostException -> {
                                    binding.tvErrorMessage.text = getString(R.string.search_paging_network_message)
                                    viewModel.setProgressBar(false)
                                }

                                is IndexOutOfBoundsException -> {
                                    Log.d("Search", "${currentState.error}")
                                    binding.tvErrorMessage.text = getString(R.string.search_paging_no_search_message)
                                    viewModel.setProgressBar(false)
                                }

                                else -> {
                                    binding.tvErrorMessage.text = getString(R.string.search_paging_error_message)
                                    viewModel.setProgressBar(false)
                                }
                            }
                        }
                        else -> { /* LoadState.NotLoading(불러온 상태), LoadState.Loading(로딩중 상태) */ }
                    }
                }
            }
        }

        /* 내 보관함에서 아이템 삭제시 북마크 상태 변경을 위한 Observer */
        lifecycleScope.launch {
            viewModel.imageUrl.collectLatest {
                it.getContentIfNotHandled()?.run {
                    adapter.bookmarkChange(this)
                }
            }
        }
    }

    private fun setSearchItemFlowObserver() {
        /* 첫번째 아이템 Observer */
        lifecycleScope.launch {
            viewModel.firstPagingData.collectLatest {
                viewModel.setProgressBar(false)
                viewModel.searchImages()
                adapter.submitData(it)
            }
        }

        /* 나머지 아이템 Observer */
        lifecycleScope.launch {
            viewModel.pagingData.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun showProgressBar() {
        binding.rvRecycler.visibility = View.GONE
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.rvRecycler.visibility = View.VISIBLE
        binding.pbLoading.visibility = View.GONE
    }
}