package com.silence.flickr.photos.ui.fullscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.silence.flickr.global.BaseActivity

class PhotoFullScreenActivity: BaseActivity() {
    override fun fragment() = PhotoFullScreenFragment().apply {
        val bundle = Bundle().apply {
            putString(PhotoFullScreenFragment.PHOTO_URL, intent.getStringExtra(INTENT_EXTRA_PARAM_PHOTO))
        }
        this.arguments = bundle
    }

    companion object {

        private const val INTENT_EXTRA_PARAM_PHOTO = "com.silence.INTENT_PARAM_PHOTO"

        fun callingIntent(context: Context, photoUrl: String): Intent {
            val intent = Intent(context, PhotoFullScreenActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_PHOTO, photoUrl)
            return intent
        }
    }
}