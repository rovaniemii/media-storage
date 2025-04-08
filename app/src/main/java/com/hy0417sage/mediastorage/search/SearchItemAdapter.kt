package com.hy0417sage.mediastorage.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.mediastorage.databinding.ItemLayoutBinding

class SearchItemAdapter(
    private val onClick : (SearchItem) -> Unit
) : PagingDataAdapter<SearchItem, SearchItemAdapter.SearchItemViewHolder>(ITEM_COMPARATOR) {
    class SearchItemViewHolder(
        private val binding: ItemLayoutBinding,
        private val onClick: (SearchItem) -> Unit,
        private val adapter: SearchItemAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchItem, position: Int) {
            binding.apply {

                Glide
                    .with(binding.root.context)
                    .load(item.imageUrl)
                    .into(binding.tvImage)

                tvDate.text = item.datetime

                if (item.bookmark) {
                    ivBookmarked.visibility = View.VISIBLE
                } else {
                    ivBookmarked.visibility = View.GONE
                }

                clLayout.setOnClickListener {
                    onClick(item)
                    adapter.bookmarkChange(
                        item = item,
                        position = position
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =
        SearchItemViewHolder(
            binding = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            onClick = onClick,
            adapter = this
        )

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.run {
            holder.bind(this, position)
        }
    }

    fun bookmarkChange(item: SearchItem, position: Int){
        val snapshotSearchItem = this@SearchItemAdapter.snapshot().firstOrNull { snapshotItem ->
            snapshotItem?.imageUrl == item.imageUrl
        }
        if(snapshotSearchItem != null) {
            snapshotSearchItem.bookmark = !snapshotSearchItem.bookmark
            this@SearchItemAdapter.notifyItemChanged(position)
        }
    }

    fun bookmarkChange(imageUrl: String){
        val snapshotSearchItem = this@SearchItemAdapter.snapshot().firstOrNull { snapshotItem ->
            snapshotItem?.imageUrl == imageUrl
        }
        if(snapshotSearchItem != null) {
            val position = this@SearchItemAdapter.snapshot().indexOf(snapshotSearchItem)
            snapshotSearchItem.bookmark = !snapshotSearchItem.bookmark
            this@SearchItemAdapter.notifyItemChanged(position)
        }
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}