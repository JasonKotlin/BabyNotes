package com.jason.babynotes.viewmodel

import SingleLiveEvent
import androidx.lifecycle.ViewModel

class ChangeViewModel: ViewModel() {
    val changeClickEvent = SingleLiveEvent<Boolean>()

    fun changeView(){
        //TODO jasonyang 如果按照這樣的寫法，我怎麼傳值進來改？
        changeClickEvent.value = true
    }
}