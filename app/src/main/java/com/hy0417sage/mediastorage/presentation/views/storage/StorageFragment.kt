package com.hy0417sage.mediastorage.presentation.views.storage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.databinding.FragmentStorageBinding
import com.hy0417sage.mediastorage.presentation.views.SharedViewModel
import com.hy0417sage.mediastorage.presentation.views.adapter.SharedAdapter
import com.hy0417sage.mediastorage.presentation.config.BaseFragment

class StorageFragment : BaseFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val sharedAdapter: SharedAdapter = SharedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = sharedAdapter
        }
        sharedViewModel.storageData.observe(viewLifecycleOwner) { listData ->
            sharedAdapter.submitList(listData)
        }
        sharedViewModel.storageDataList()
    }
}