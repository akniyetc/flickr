package com.silence.flickr.photos.ui

import com.silence.flickr.R
import com.silence.flickr.common.BaseActivity

class PhotosActivity : BaseActivity() {
    override val layoutRes = R.layout.activity_photos

    override fun fragment() = PhotosFragment()
}
