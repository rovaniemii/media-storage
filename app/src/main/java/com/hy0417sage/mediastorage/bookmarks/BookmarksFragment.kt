package com.hy0417sage.mediastorage.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.core.ui.BaseFragment
import com.hy0417sage.core.util.Constants
import com.hy0417sage.core.util.getImageList
import com.hy0417sage.core.util.removeImageFromList
import com.hy0417sage.mediastorage.databinding.FragmentBookmarksBinding
import com.hy0417sage.mediastorage.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
* 두 번째 fragment : 내 보관함
 */

@AndroidEntryPoint
class BookmarksFragment :
    BaseFragment<FragmentBookmarksBinding>(FragmentBookmarksBinding::inflate) {

    private val viewModel: SharedViewModel by activityViewModels() /* Fragment간 정보 교환을 위한 activityViewModel */

    private val adapter = BookmarksItemAdapter(
        /* 내 보관함 탭에서 아이템을 클릭했을 때 */
        onClick = { item, adapter ->
            activity?.run {
                if (isFinishing)
                    return@run
                adapter.removeItem(item.imageUrl)
                viewModel.removeImage(item.imageUrl)
                removeImageFromList(item)
            }
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* 초기 뷰 세팅 */
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        activity?.run {
            if (isFinishing)
                return@run

            val bookmarksList = getImageList(Constants.BOOKMARKED_LIST)
            if (bookmarksList.size == 0)
                binding.textViewBookmarks.visibility = View.VISIBLE
            else
                binding.textViewBookmarks.visibility = View.GONE
            adapter.submitList(bookmarksList)
        }
    }
}
