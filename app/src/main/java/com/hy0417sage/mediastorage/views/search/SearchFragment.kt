package com.hy0417sage.mediastorage.views.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hy0417sage.mediastorage.base.BaseFragment
import com.hy0417sage.mediastorage.databinding.FragmentSearchBinding
import com.hy0417sage.mediastorage.views.SharedViewModel

/*
* 첫 번째 fragment : 검색 결과 화면
* */
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SharedViewModel by activityViewModels()
    private val searchAdapter: SearchAdapter = SearchAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            if (viewModel.keyWord != binding.searchEditText.text.toString()) {
                viewModel.keyWord = binding.searchEditText.text.toString()
                viewModel.searchItem(viewModel.keyWord, 1)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textErrorMessage.text = viewModel.errorMessage.value
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }

        viewModel.searchItem.observe(viewLifecycleOwner) { searchDataList ->
            searchAdapter.submitList(searchDataList)
        }

        searchAdapter.setItemClickListener { searchData ->
            viewModel.updateBookmarks(searchData)
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!binding.recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    viewModel.searchItem(binding.searchEditText.text.toString())
                }
            }
        })
    }
}