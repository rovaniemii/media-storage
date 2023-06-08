package com.hy0417sage.mediastorage.views.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.mediastorage.base.BaseFragment
import com.hy0417sage.mediastorage.databinding.FragmentStorageBinding
import com.hy0417sage.mediastorage.views.SharedViewModel

/*
* 두 번째 fragment : 내 보관함
 */
class BookmarksFragment : BaseFragment<FragmentStorageBinding>(FragmentStorageBinding::inflate) {

    private val viewModel: SharedViewModel by activityViewModels()
    private val bookmarksAdapter: BookmarksAdapter = BookmarksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setBookmarks()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarksAdapter
        }

        //검색 결과에서 보관했던 이미지들이 보관한 순서대로 보입니다.
        //보관한 이미지 리스트는 앱 재시작 후 다시 보여야 합니다. (DB 관련 라이브러리 사용 금지. SharedPreferences 사용 권장)
        viewModel.bookmarksList.observe(viewLifecycleOwner) { storageDataList ->
            bookmarksAdapter.submitList(storageDataList)
        }
    }
}