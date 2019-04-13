package com.silence.flickr

import com.silence.flickr.global.BaseActivity
import com.silence.flickr.photos.ui.PhotosFragment

class MainActivity : BaseActivity() {
    override fun fragment() = PhotosFragment()
}
