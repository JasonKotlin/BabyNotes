package com.jason.babynotes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jason.babynotes.R
import com.jason.babynotes.viewmodel.GetTimeViewModel

class MVVMActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
        val myViewModel = GetTimeViewModel()
        myViewModel.viewModelGetTime()
        myViewModel.bindingData.updateTime
        //TODO activity_mvvm 裡面有註解要問的
    }
}