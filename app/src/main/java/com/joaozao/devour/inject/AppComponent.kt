package com.joaozao.devour.inject

import com.joaozao.devour.DevourApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<DevourApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DevourApplication>()
}