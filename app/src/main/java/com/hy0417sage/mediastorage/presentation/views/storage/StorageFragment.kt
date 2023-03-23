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
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val sharedAdapter: SharedAdapter = SharedAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sharedAdapter
        }
        sharedViewModel.storageData.observe(viewLifecycleOwner) { listData ->
            sharedAdapter.submitList(listData)
        }
        sharedViewModel.storageDataList()

        sharedAdapter.setItemClickListener { data ->
            // 데이터가 클릭되면 pref 삭제
            sharedPreference.deleteValue(data.thumbnail)
            sharedViewModel.storageDataList()
        }
    }
}