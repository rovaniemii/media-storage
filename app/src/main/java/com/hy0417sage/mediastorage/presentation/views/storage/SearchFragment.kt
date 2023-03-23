package com.hy0417sage.mediastorage.presentation.views.storage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.hy0417sage.mediastorage.ApplicationClass.Companion.sharedPreference
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.databinding.FragmentSearchBinding
import com.hy0417sage.mediastorage.presentation.views.SharedViewModel
import com.hy0417sage.mediastorage.presentation.views.adapter.SharedAdapter
import com.hy0417sage.mediastorage.presentation.config.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val sharedAdapter: SharedAdapter = SharedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearch()
        initLike()
    }

    private fun initSearch(){
        binding.searchButton.setOnClickListener {
            //TODO (1)빈 값 검색 시 HTTP 400 오류처리, (2)검색 결과가 안나올 경우 text message 처리
            sharedViewModel.thumbnailSearch(binding.searchEditText.text.toString())
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = sharedAdapter
        }
        sharedViewModel.searchData.observe(viewLifecycleOwner) { searchDataList ->
            sharedAdapter.submitList(searchDataList)
        }
    }

    private fun initLike(){
        sharedAdapter.setItemClickListener { viewData ->
            if (sharedPreference.getValue(viewData.thumbnail) == null) {
                sharedPreference.setValue(viewData.thumbnail, viewData)
            }
        }
    }
}