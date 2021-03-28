package com.jason.babynotes.model

import android.util.Log
import android.widget.TextView

data class HomeListData(
        val leftView: TextView,
        val listName: TextView,
        val listNoUse: TextView,
        val listAlert: TextView) {

    class HomeItemList: ArrayList<HomeListData>()

    val defaultList = arrayListOf("餵食","換尿布","睡眠","擠奶","其他活動")
    var itemList = ArrayList<String>()

    open fun getListData(){
        for (i in 0 until defaultList.size){
            itemList.add(defaultList.get(i))
        }

        for(i in 0 until defaultList.size){
            var number: Int = i + 1
            for(j in 0 until defaultList.size){
                var str : String = defaultList.get(j) + number
                itemList.add(str)
            }
        }
    }
}