package com.example.laba_5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yandex.mapkit.geometry.Point

open class DataModel : ViewModel() {
    val messageNumberButton: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }
    val messageClickButton: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }
}