package com.hy0417sage.mediastorage.views.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hy0417sage.mediastorage.databinding.FragmentStorageBinding
import com.hy0417sage.mediastorage.databinding.FragmentStorageBinding.inflate
import com.hy0417sage.mediastorage.views.SharedViewModel
import com.hy0417sage.mediastorage.views.adapter.StorageAdapter

/*
* 두 번째 fragment : 내 보관함
 */
class StorageFragment : Fragment() {
    private var _binding: FragmentStorageBinding? = null
    val binding: FragmentStorageBinding get() = _binding!!

    private val viewModel: SharedViewModel by activityViewModels()
    private val storageAdapter: StorageAdapter = StorageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setStorageDataList()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = storageAdapter
        }

        //검색 결과에서 보관했던 이미지들이 보관한 순서대로 보입니다.
        //보관한 이미지 리스트는 앱 재시작 후 다시 보여야 합니다. (DB 관련 라이브러리 사용 금지. SharedPreferences 사용 권장)
        viewModel.storageDataList.observe(viewLifecycleOwner) { storageDataList ->
            storageAdapter.submitList(storageDataList)
        }
    }
}