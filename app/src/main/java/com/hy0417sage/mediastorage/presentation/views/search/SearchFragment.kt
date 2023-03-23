package com.hy0417sage.mediastorage.presentation.views.search

import android.os.Bundle
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
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val sharedAdapter: SharedAdapter = SharedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //검색
        binding.searchButton.setOnClickListener {
            //TODO (1)빈 값 검색 시 HTTP 400 오류처리, (2)검색 결과가 안나올 경우 text message 처리
            sharedViewModel.thumbnailSearch(binding.searchEditText.text.toString())
        }

        //리사이클러뷰 셋팅
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sharedAdapter
        }

        //검색 데이터 보여주기
        sharedViewModel.searchData.observe(viewLifecycleOwner) { searchData ->
            sharedAdapter.submitList(searchData)
        }

        //특정 데이터 클릭 하면 데이터 저장
        sharedAdapter.setItemClickListener { searchData ->
            // 만약 저장 되어진 데이터면 동작 x
            if (sharedPreference.getValue(searchData.thumbnail) == null) {
                sharedPreference.setValue(searchData.thumbnail, searchData.copy(like = true))
                sharedViewModel.storageDataList()
            }
            sharedViewModel.updateLike()
        }
    }
}