package com.hy0417sage.mediastorage.presentation.views.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.mediastorage.ApplicationClass.Companion.sharedPreference
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.databinding.FragmentSearchBinding
import com.hy0417sage.mediastorage.presentation.config.BaseFragment
import com.hy0417sage.mediastorage.presentation.views.SharedViewModel
import com.hy0417sage.mediastorage.presentation.views.adapter.SharedAdapter

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SharedViewModel by activityViewModels()
    private val sharedAdapter: SharedAdapter = SharedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            //TODO (1)빈 값 검색 시 HTTP 400 오류처리, (2)검색 결과가 안나올 경우 text message 처리
            viewModel.thumbnailSearch(binding.searchEditText.text.toString())
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sharedAdapter
        }

        viewModel.searchDataList.observe(viewLifecycleOwner) { searchDataList ->
            sharedAdapter.submitList(searchDataList)
        }

        sharedAdapter.setItemClickListener { addStorageData -> //클릭해서 StorageData 추가 후 정렬
            viewModel.storageSorted(addStorageData)
        }
    }
}