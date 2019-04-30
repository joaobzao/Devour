package com.joaozao.devour

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.joaozao.devour.databinding.MainActivityBinding

class DevourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : MainActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.main_activity)

        binding.lifecycleOwner = this
    }
}
