package com.jason.babynotes.viewmodel

import androidx.databinding.BaseObservable
import androidx.lifecycle.ViewModel
import com.jason.babynotes.model.GetTimeModel

class GetTimeViewModel: ViewModel() {
    fun viewModelGetTime(){
        //TODO 這樣寫不行耶，只是要進去抓時間，但這樣寫好像變抓這個元件？
        var time: String = GetTimeModel::getTime.toString()
        bindingData.updateTime = "更新時間：${time}"
        bindingData.notifyChange()
    }

    val bindingData: BindingData = BindingData("")
    data class BindingData(var updateTime: String) : BaseObservable()

    //TODO 人家可以繼承 BaseViewModel 但我根本就沒有這個東西 = ="
}