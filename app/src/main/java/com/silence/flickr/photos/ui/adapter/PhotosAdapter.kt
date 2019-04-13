package com.silence.flickr.photos.ui.adapter

import android.support.v7.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.silence.flickr.photos.domain.entity.Photo

class PhotosAdapter: ListDelegationAdapter<MutableList<Any>>() {

    internal var onPhotoClickListener: (Photo) -> Unit = {}
    internal var onBottomReachedListener: () -> Unit = {}

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(PhotoAdapterDelegate(onPhotoClickListener))
        delegatesManager.addDelegate(PhogressAdapterDelegate())
    }

    fun setData(newPhotos: List<Photo>) {
        val progress = isProgress()

        items.clear()
        items.addAll(newPhotos)
        if (progress) items.add(ProgressItem())

        notifyItemRangeInserted(itemCount, itemCount + newPhotos.size)
    }

    fun showProgress(isVisible: Boolean) {
        val currentProgress = isProgress()

        if (isVisible && !currentProgress){
            items.add(ProgressItem())
            notifyItemInserted(items.lastIndex)
        }
        else if (!isVisible && currentProgress){
            items.remove(items.last())
            notifyItemRemoved(items.lastIndex)
        }

    }

    private fun isProgress() = items.isNotEmpty() && items.last() is ProgressItem

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any?>) {
        super.onBindViewHolder(holder, position, payloads)

        if (position == items.size - 5) onBottomReachedListener.invoke()
    }
}