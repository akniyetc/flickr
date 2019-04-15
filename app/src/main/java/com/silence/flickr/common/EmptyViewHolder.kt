package com.silence.flickr.common

import android.view.ViewGroup
import com.silence.flickr.R
import com.silence.flickr.common.extension.visible
import kotlinx.android.synthetic.main.layout_zero.view.*

class EmptyViewHolder(
    private val view: ViewGroup,
    private val refreshListener: () -> Unit = {}
) {

    init {
        view.refreshButton.setOnClickListener { refreshListener() }
    }

    fun showEmptyData() {
        view.titleTextView.text = view.resources.getText(R.string.empty_data)
        view.descriptionTextView.text = view.resources.getText(R.string.empty_data_description)
        view.visible(true)
    }

    fun showEmptyError(msg: String? = null) {
        view.titleTextView.text = view.resources.getText(R.string.empty_error)
        view.descriptionTextView.text = msg ?: view.resources.getText(R.string.empty_error_description)
        view.visible(true)
    }

    fun hide() {
        view.visible(false)
    }
}