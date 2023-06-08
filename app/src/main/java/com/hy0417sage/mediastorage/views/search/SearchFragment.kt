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

        //검색어를 입력할 수 있습니다.
        binding.searchButton.setOnClickListener {
            if (viewModel.keyWord != binding.searchEditText.text.toString()) {
                viewModel.keyWord = binding.searchEditText.text.toString()
                viewModel.thumbnailSearch(viewModel.keyWord, 1)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.textErrorMessage.text = viewModel.errorMessage.value
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }

        //스크롤을 통해 다음 페이지를 불러옵니다.
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                if (!binding.recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    viewModel.thumbnailSearch(binding.searchEditText.text.toString())
                }
            }
        })

        //검색된 이미지 리스트가 나타납니다. 각 아이템에는 이미지와 함께 날짜와 시간을 표시합니다.
        //이미 보관된 이미지는 특별한 표시를 보여줍니다. (좋아요/별표/하트 등)
        viewModel.searchDataList.observe(viewLifecycleOwner) { searchDataList ->
            searchAdapter.submitList(searchDataList)
        }

        //리스트에서 특정 이미지를 선택하여 '내 보관함'으로 저장할 수 있습니다.
        //보관된 이미지를 다시 선택하여 보관함에서 제거 가능합니다.
        searchAdapter.setItemClickListener { searchData ->
            viewModel.updateStorage(searchData)
        }
    }
}