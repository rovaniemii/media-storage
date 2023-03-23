package com.hy0417sage.mediastorage.presentation.views.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.mediastorage.databinding.LayoutViewHolderBinding
import com.hy0417sage.mediastorage.domain.model.ViewData

class SharedViewHolder(
    private val binding: LayoutViewHolderBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(viewData: ViewData) {
        with(binding) {
            Glide.with(binding.root)
                .load(viewData.thumbnail)
                .into(imageView)
            binding.dateTime.text = viewData.datetime.split('T')[0]

            if(!viewData.like){
                binding.likeView.visibility = View.GONE
            }
        }
    }
}