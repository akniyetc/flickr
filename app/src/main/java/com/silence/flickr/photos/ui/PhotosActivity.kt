package com.silence.flickr.photos.ui

import android.os.Bundle
import com.silence.flickr.R
import com.silence.flickr.common.BaseActivity
import com.silence.flickr.common.BaseFragment
import com.silence.flickr.common.extension.inTransaction

class PhotosActivity : BaseActivity() {

    override val layoutRes = R.layout.activity_photos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction { add(
            R.id.fragmentContainer, PhotosFragment()) }

}
