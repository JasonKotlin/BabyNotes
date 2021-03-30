package com.jason.babynotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecyclerViewModel: ViewModel() {
    var text = MutableLiveData<String>()
}