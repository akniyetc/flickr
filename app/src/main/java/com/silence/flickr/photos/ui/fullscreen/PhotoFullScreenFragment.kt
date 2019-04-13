package com.silence.flickr.photos.ui.fullscreen

import com.silence.flickr.R
import com.silence.flickr.global.BaseFragment

class PhotoFullScreenFragment: BaseFragment() {
    override val layoutRes = R.layout.fragment_photo_full_screen

    companion object {
        const val PHOTO_URL = "full_screen_photo_url"
    }
}