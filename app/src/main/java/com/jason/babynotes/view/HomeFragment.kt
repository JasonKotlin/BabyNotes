package com.jason.babynotes.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jason.babynotes.R
import com.jason.babynotes.baseclass.BaseFragment
import com.jason.babynotes.databinding.HomeFragmentBinding
import com.jason.babynotes.databinding.HomeItemLayoutBinding
import com.jason.babynotes.viewmodel.HomeFeaturesViewModel


class HomeFragment: BaseFragment() {
    val mViewModel by viewModels<HomeFeaturesViewModel>()
    lateinit var mBinding: HomeFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = HomeFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            featuresViewModel = mViewModel
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        createRecyclerView()
    }

    private fun setObserver() {
//        mViewModel.changeClickEvent.observe(viewLifecycleOwner, Observer {
//            if (it) {
//                getBaseActivity().transactionFragment(ClickExerciseFragment(), true, isPop = false)
//            }
//        })
    }

    /**
     * 1. 圓角無效？
     * 2. RecyclerView 沒辦法滑到更下面？
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun createRecyclerView(){
        val mRecyclerView: RecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = ItemAdapter(mViewModel.mListData)
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.recycler_divider_line))
        mRecyclerView.addItemDecoration(itemDecoration)
        mRecyclerView.layoutManager = LinearLayoutManager(getBaseActivity())
    }

    inner class ItemAdapter(val items: List<String>, ) : RecyclerView.Adapter<ItemAdapter.MyHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            var binding : HomeItemLayoutBinding = HomeItemLayoutBinding.bind(LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_item_layout, parent, false))
            return MyHolder(binding)
        }

        override fun getItemCount(): Int {
            return if(items.isNotEmpty()) items.size else {
                0
            }
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.homeBinding.homeListFeaturesName.text = items.get(position)
            holder.homeBinding.homeListIcon.setImageResource(
                    when(holder.homeBinding.homeListFeaturesName.text){
                        "餵食"-> R.drawable.baby_bottle
                        "換尿布"-> R.drawable.diaper
                        "睡眠"-> R.drawable.baby_bed
                        "擠奶"-> R.drawable.milking
                        "其他活動"-> R.drawable.other_doings
                    else ->{
                        R.drawable.baby_bed
                }
            })

            holder.homeBinding.homeListIcon.setOnClickListener {
                if(position == 0){
                    getBaseActivity().transactionFragment(TaipeiYouBike(), false, isPop = false)
                }
            }

            holder.itemView.setOnClickListener{
                Log.d("JasonYang","點擊Item")
            }

            holder.bind()
        }

        inner class MyHolder(var homeBinding: HomeItemLayoutBinding) : RecyclerView.ViewHolder(homeBinding.root){
            fun bind(){
                //executePendingBindings 非常重要，加了才有辦法自動更新資料
                homeBinding.executePendingBindings()
            }
        }
    }
}