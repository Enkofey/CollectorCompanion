package com.example.collectorcompanion.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.collectorcompanion.Model.Entity.Collection
import com.example.collectorcompanion.R

class CollectionAdapter : ListAdapter<Collection, CollectionAdapter.CollectionViewHolder>(CollectionsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        return CollectionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.collectionName)
    }

    class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): CollectionViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return CollectionViewHolder(view)
            }
        }
    }
}
class CollectionsComparator : DiffUtil.ItemCallback<Collection>() {
        override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
            return oldItem.collectionName == newItem.collectionName
        }
}


