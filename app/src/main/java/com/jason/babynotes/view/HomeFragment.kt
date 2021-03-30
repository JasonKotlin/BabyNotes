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
import com.jason.babynotes.databinding.HomeItemLayoutBinding
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
     * 1. 圓角無效？
     * 2. RecyclerView 沒辦法滑到更下面？
     * 3. RecyclerView 的 Binding 似乎哪邊錯了？ view 沒辦法用 home_item_layout 裡面的元件？
     *
     */
    private fun createRecyclerView(){
        val mRecyclerView: RecyclerView = mBinding.recyclerView

        mRecyclerView.adapter = ItemAdapter(mViewModel.mListData)
        mRecyclerView.addItemDecoration(getRecyclerViewDivider(R.drawable.recycler_divider_line)!!)
        mRecyclerView.layoutManager = LinearLayoutManager(getBaseActivity())
    }

    class ItemAdapter(val items: List<String>) : RecyclerView.Adapter<ItemAdapter.viewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            var binding : HomeItemLayoutBinding = HomeItemLayoutBinding.bind(LayoutInflater.from(parent.context)
                    .inflate(R.layout.home_item_layout, parent, false)).apply {
                        //TODO 這裡會有要幹嘛嗎？
            }
            return viewHolder(binding.root)
        }

        override fun getItemCount(): Int {
            return if(items.isNotEmpty()) items.size else {
                0
            }
        }

        override fun onBindViewHolder(holder: viewHolder, position: Int) {
            holder.home_list_features_name.text = items.get(position)
            holder.home_list_icon.setImageResource(
                    when(holder.home_list_features_name.text){
                        "餵食"-> R.drawable.baby_bottle
                        "換尿布"-> R.drawable.diaper
                        "睡眠"-> R.drawable.baby_bed
                        "擠奶"-> R.drawable.milking
                        "其他活動"-> R.drawable.other_doings
                    else ->{
                        R.drawable.baby_bed
                }
            })

            holder.itemView.setOnClickListener{
                //點擊Item時做的事
            }
        }

        //TODO 怎麼還會看到 findViewById，是還要再建一個 Binding？
        //TODO 哪裡寫錯了嗎？我的 view 取不到我 home_item_layout 裡的東西…
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