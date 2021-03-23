package com.jason.babynotes.baseclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jason.babynotes.R

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun transactionFragment(fragment: BaseFragment, isAdd: Boolean, isPop: Boolean){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if(isPop){
            if(supportFragmentManager.backStackEntryCount > 0){
                supportFragmentManager.popBackStack()
                return
            }else{
                //TODO 跳 Dialog 選擇是否要離開程式
            }
        }else if(isAdd){
            if(!fragment.isAdded){
                fragmentTransaction.add(R.id.base_main_container, fragment)
                fragmentTransaction.addToBackStack(fragment.javaClass.name)
            }else{
                return
            }
        }else{
            fragmentTransaction.replace(R.id.base_main_container, fragment)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }
}