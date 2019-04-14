package com.silence.flickr.photos.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.silence.flickr.R
import com.silence.flickr.global.extension.inflate

class ProgressAdapterDelegate: AdapterDelegate<MutableList<Any>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ProgressViewHolder(parent.inflate(R.layout.item_progress))
    }

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean {
        return items[position] is ProgressItem
    }

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) { }

    inner class ProgressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}