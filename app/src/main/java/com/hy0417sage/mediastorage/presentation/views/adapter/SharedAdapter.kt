package com.hy0417sage.mediastorage.presentation.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hy0417sage.mediastorage.databinding.LayoutViewHolderBinding
import com.hy0417sage.mediastorage.domain.model.ViewData

class SharedAdapter : ListAdapter<ViewData, RecyclerView.ViewHolder>(SharedDiffUtil()) {

    private lateinit var onItemClickListener: OnItemClickListener

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
            holder.itemView.setOnClickListener {
                onItemClickListener.onClick(viewData)
            }
        }
    }

    fun interface OnItemClickListener {
        fun onClick(viewData: ViewData)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}