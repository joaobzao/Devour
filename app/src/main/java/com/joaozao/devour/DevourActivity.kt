package com.joaozao.devour

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.joaozao.devour.ui.main.MainFragment

class DevourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
