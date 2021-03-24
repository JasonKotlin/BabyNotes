package com.jason.babynotes.viewmodel

import SingleLiveEvent
import androidx.lifecycle.ViewModel

class ChangeViewModel: ViewModel() {
    val changeClickEvent = SingleLiveEvent<Boolean>()

    fun changeView(){
        changeClickEvent.value = true
    }
}