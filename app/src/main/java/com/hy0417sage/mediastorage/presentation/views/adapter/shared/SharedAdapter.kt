package com.hy0417sage.mediastorage.presentation.views.adapter.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hy0417sage.mediastorage.databinding.LayoutViewHolderBinding
import com.hy0417sage.mediastorage.domain.model.ViewData

abstract class SharedAdapter : ListAdapter<ViewData, RecyclerView.ViewHolder>(SharedDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return SharedViewHolder(
            LayoutViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is SharedViewHolder) {
            val viewData = getItem(position) as ViewData
            holder.bind(viewData)
        }
    }
}