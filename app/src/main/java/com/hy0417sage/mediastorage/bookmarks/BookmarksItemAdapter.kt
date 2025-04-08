package com.hy0417sage.mediastorage.bookmarks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hy0417sage.core.model.SearchItem
import com.hy0417sage.mediastorage.databinding.ItemLayoutBinding

class BookmarksItemAdapter(
    private val onClick: (SearchItem, BookmarksItemAdapter) -> Unit
) : ListAdapter<SearchItem, BookmarksItemAdapter.BookmarksItemViewHolder>(diffUtil) {
    class BookmarksItemViewHolder(
        private val binding: ItemLayoutBinding,
        private val onClick: (SearchItem, BookmarksItemAdapter) -> Unit,
        private val adapter: BookmarksItemAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchItem) {
            Glide
                .with(binding.root.context)
                .load(item.imageUrl)
                .into(binding.tvImage)

            binding.clLayout.setOnClickListener {
                onClick(item, adapter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksItemViewHolder {
        return BookmarksItemViewHolder(
            binding = ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick,
            adapter = this
        )
    }

    override fun onBindViewHolder(holder: BookmarksItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun removeItem(imageUrl: String): Boolean {
        val newList = currentList.toMutableList()
        newList.forEachIndexed { index, item ->
            if (item.imageUrl == imageUrl) {
                newList.removeAt(index)
                submitList(newList)
                return true
            }
        }
        return false
    }

    //링크: https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}