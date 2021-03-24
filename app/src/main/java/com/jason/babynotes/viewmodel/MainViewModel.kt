package com.jason.babynotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var title = MutableLiveData("首頁")

    fun btn1ChangeBackground(){
        title.value = "被1改"
    }

    fun btn2ChangeText(){
        title.value = "被2改"
    }
}