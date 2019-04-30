package com.joaozao.devour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.joaozao.devour.databinding.MainActivityBinding

class DevourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : MainActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.main_activity)

        binding.lifecycleOwner = this
    }
}
