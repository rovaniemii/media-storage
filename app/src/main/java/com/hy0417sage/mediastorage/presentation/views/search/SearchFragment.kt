package com.hy0417sage.mediastorage.presentation.views.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.databinding.FragmentSearchBinding
import com.hy0417sage.mediastorage.presentation.config.BaseFragment
import com.hy0417sage.mediastorage.presentation.views.SharedViewModel
import com.hy0417sage.mediastorage.presentation.views.adapter.SearchAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SharedViewModel by activityViewModels()
    private val searchAdapter: SearchAdapter = SearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            //TODO (1)빈 값 검색 시 HTTP 400 오류처리, (2)검색 결과가 안나올 경우 text message 처리
            viewModel.thumbnailSearch(binding.searchEditText.text.toString())
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }

        viewModel.searchDataList.observe(viewLifecycleOwner) { searchDataList ->
            searchAdapter.submitList(searchDataList)
            Log.d("search", "반영 되는거 맞아????????????? $searchDataList")
        }

        searchAdapter.setItemClickListener { searchData ->
            viewModel.addStorage(searchData)
        }
    }
}