package com.silence.flickr.global

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.silence.flickr.R
import com.silence.flickr.global.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
                R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTransaction { add(
                    R.id.fragmentContainer, fragment()) }

    abstract fun fragment(): BaseFragment
}