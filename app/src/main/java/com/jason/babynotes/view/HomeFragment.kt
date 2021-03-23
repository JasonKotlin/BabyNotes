package com.jason.babynotes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jason.babynotes.baseclass.BaseFragment
import com.jason.babynotes.databinding.HomeFragmentBinding
import com.jason.babynotes.viewmodel.ChangeViewModel

class HomeFragment: BaseFragment() {
    private val mViewModel by viewModels<ChangeViewModel>()
    lateinit var mBinding: HomeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = HomeFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            homeViewModel = mViewModel
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        mViewModel.changeClickEvent.observe(viewLifecycleOwner, Observer {
            if (it) {
                //TODO jasonyang 等想到第二頁要幹嘛再來寫
//                getBaseActivity().transactionFragment(ClickExerciseFragment(), true, isPop = false)
            }
        })
    }
}