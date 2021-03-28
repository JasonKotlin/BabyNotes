package com.jason.babynotes.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var title = MutableLiveData("首頁")

    fun btn1Click(){
        title.value = "熱門功能1"
    }

    fun btn2ChangeText(){
        title.value = "熱門功能2"
    }
}