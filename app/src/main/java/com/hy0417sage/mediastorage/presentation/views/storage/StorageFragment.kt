package com.hy0417sage.mediastorage.presentation.views.storage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.databinding.FragmentStorageBinding
import com.hy0417sage.mediastorage.presentation.config.BaseFragment
import com.hy0417sage.mediastorage.presentation.views.SharedViewModel
import com.hy0417sage.mediastorage.presentation.views.adapter.StorageAdapter

class StorageFragment : BaseFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    private val viewModel: SharedViewModel by activityViewModels()
    private val storageAdapter: StorageAdapter = StorageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setStorageDataList()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = storageAdapter
        }

        viewModel.storageDataList.observe(viewLifecycleOwner) { storageDataList ->
            storageAdapter.submitList(storageDataList)
        }
    }
}