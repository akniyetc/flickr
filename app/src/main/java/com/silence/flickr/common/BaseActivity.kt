package com.silence.flickr.common

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.silence.flickr.R
import com.silence.flickr.common.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : MvpAppCompatActivity() {

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        setSupportActionBar(toolbar)
    }
}