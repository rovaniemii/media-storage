package com.hy0417sage.mediastorage.presentation.views.storage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.mediastorage.ApplicationClass.Companion.sharedPreference
import com.hy0417sage.mediastorage.R
import com.hy0417sage.mediastorage.databinding.FragmentStorageBinding
import com.hy0417sage.mediastorage.presentation.views.adapter.SharedAdapter
import com.hy0417sage.mediastorage.presentation.config.BaseFragment
import com.hy0417sage.mediastorage.presentation.views.SharedViewModel

class StorageFragment : BaseFragment<FragmentStorageBinding>(R.layout.fragment_storage) {
    private val viewModel: SharedViewModel by activityViewModels()
    private val sharedAdapter: SharedAdapter = SharedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setStorageDataList()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sharedAdapter
        }

        viewModel.storageDataList.observe(viewLifecycleOwner) { storageDataList ->
            sharedAdapter.submitList(storageDataList)
        }

        sharedAdapter.setItemClickListener { data ->
            sharedPreference.deleteValue(data.thumbnail)
            viewModel.setStorageDataList()
        }
    }
}