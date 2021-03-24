package com.jason.babynotes.baseclass

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
                AlertDialog.Builder(this).apply {
                    setTitle("提示訊息")
                    setMessage("確定要離開程式？")
                    setPositiveButton("確定", ) { _, _ ->
                        finish()
                    }
                    setNegativeButton("取消"){ _, _ ->
                        Toast.makeText(context, "反悔不離開了", Toast.LENGTH_SHORT).show()
                    }
                    show()
                }
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

    override fun onBackPressed() {
        AlertDialog.Builder(this@BaseActivity).apply {
            setTitle("提示訊息")
            setMessage("確定要離開程式？")
            //本來應該要傳兩個參數，一個是 dialog，一個是 which 但這裡都不需要用到就可以用 _ 來取代
            setPositiveButton("確定", ) { _, _ ->
                finish()
            }
            setNegativeButton("取消"){ _, _ ->
                Toast.makeText(context, "反悔不離開了", Toast.LENGTH_SHORT).show()
            }
            show()
        }
    }
}