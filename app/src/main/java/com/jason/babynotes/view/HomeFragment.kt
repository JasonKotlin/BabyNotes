package com.jason.babynotes.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
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
        //TODO 改成這樣寫就不用使用 !! 了，但為什麼語法糖又說不需要 ?
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        R.drawable.recycler_divider_line?.let {
            itemDecoration.setDrawable(resources.getDrawable(it))
        }
        mRecyclerView.addItemDecoration(itemDecoration)
        mRecyclerView.layoutManager = LinearLayoutManager(getBaseActivity())
    }

    interface RecyclerViewClickListener{
        fun onRecyclerViewItemClickListener()
    }

    class ItemAdapter(val items: List<String>, ) : RecyclerView.Adapter<ItemAdapter.MyHolder>(){
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

            //TODO OnClickListener事件應該可以拔到 ViewModel 裡面去做，這邊應該是做 observer 監聽點了要幹嘛對吧？
            holder.homeBinding.homeListIcon.setOnClickListener {
                Log.d("JasonYang", "點擊ICON$position")
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