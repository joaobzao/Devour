package com.joaozao.devour.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel() {
    private val _welcome = MutableLiveData("Welcome to main_fragment")

    val welcome : LiveData<String> = _welcome
}
