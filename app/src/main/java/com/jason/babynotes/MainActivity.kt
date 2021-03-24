package com.jason.babynotes

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jason.babynotes.baseclass.BaseActivity
import com.jason.babynotes.databinding.ActivityMainBinding
import com.jason.babynotes.view.HomeFragment
import com.jason.babynotes.viewmodel.MainViewModel

class MainActivity : BaseActivity() {
    private val mViewModel by viewModels<MainViewModel>()
    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.apply {
            lifecycleOwner = this@MainActivity
            mainViewModel = mViewModel

            back.setOnClickListener(View.OnClickListener {
                if(supportFragmentManager.backStackEntryCount > 0)
                    supportFragmentManager.popBackStack()
                else{
                    finish()
                }
            })

        }

        setContentView(mBinding.root)
        transactionFragment(HomeFragment(),false, isPop = false)
    }
}