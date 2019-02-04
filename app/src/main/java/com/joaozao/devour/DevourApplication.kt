package com.joaozao.devour

import com.joaozao.devour.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class DevourApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}