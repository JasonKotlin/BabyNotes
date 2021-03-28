package com.jason.babynotes.view

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
import com.jason.babynotes.viewmodel.HomeFeaturesViewModel


class HomeFragment: BaseFragment() {
    val mViewModel by viewModels<HomeFeaturesViewModel>()
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
     * Q1.每一個 Item 裡面的資料應該可以建一個 Model 出來，我就取那個 Model 來用就好
     * Q2.如果我用了 holder.itemView.setOnItemclick，這樣我就沒辦法針對最右邊的鬧鐘按鈕做事了對吧？
     *   網路上的 interface 寫法也是亂七八糟的看不懂 = =" 求教
     * Q3.有沒有辦法讓 RecyclerView 的高度就是在最下面的 View 上面？
     *
     * TODO 圓角還沒做
     */
    private fun createRecyclerView(){
        val mRecyclerView: RecyclerView = mBinding.recyclerView

        mRecyclerView.adapter = ItemAdapter(mViewModel.mListData)
        mRecyclerView.addItemDecoration(getRecyclerViewDivider(R.drawable.recycler_divider_line)!!)
        mRecyclerView.layoutManager = LinearLayoutManager(getBaseActivity())
    }

    class ItemAdapter(val items: List<String>) : RecyclerView.Adapter<ItemAdapter.viewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item_layout, parent, false)
            return viewHolder(view)
        }

        override fun getItemCount(): Int {
            return if(items.isNotEmpty()) items.size else {
                0
            }
        }

        override fun onBindViewHolder(holder: viewHolder, position: Int) {
            holder.home_list_features_name.text = items.get(position)
            holder.itemView.setOnClickListener{
                Log.d("JasonYang", "按！$position")
            }
        }

        //TODO 怎麼還會看到 findViewById，是還要再建一個 Binding？
        inner class viewHolder(val view: View) : RecyclerView.ViewHolder(view){
            val home_list_icon = view.findViewById<ImageView>(R.id.home_list_icon)
            val home_list_features_name = view.findViewById<TextView>(R.id.home_list_features_name)
            val home_list_alert = view.findViewById<ImageView>(R.id.home_list_alert)
        }
    }

    private fun getRecyclerViewDivider(@DrawableRes drawableId: Int): ItemDecoration? {
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(drawableId))
        return itemDecoration
    }
}