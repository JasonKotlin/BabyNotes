package com.jason.babynotes.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jason.babynotes.R
import com.jason.babynotes.baseclass.BaseFragment
import com.jason.babynotes.databinding.TaipeiYoubikeBinding
import com.jason.babynotes.model.YouBikeStationData
import com.jason.babynotes.viewmodel.YouBikeViewModel
import androidx.databinding.library.baseAdapters.BR

/**
 * API名稱：YouBike臺北市公共自行車即時資訊
 * API網址：https://tcgbusfs.blob.core.windows.net/blobyoubike/YouBikeTP.json
 */
class TaipeiYouBike : BaseFragment() {
    val YouBikeViewModel by viewModels<YouBikeViewModel>()
    lateinit var taipeiYoubikeBinding: TaipeiYoubikeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        taipeiYoubikeBinding = TaipeiYoubikeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return taipeiYoubikeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        YouBikeViewModel.startRetrieveData()
    }

    private fun initView() {
        val layoutManager = LinearLayoutManager(getBaseActivity())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        taipeiYoubikeBinding.recyclerView.layoutManager = layoutManager
    }

    private fun initData() {
        YouBikeViewModel.stationList.observe(getBaseActivity(), Observer {
            taipeiYoubikeBinding.recyclerView.adapter = YouBikeStationAdapter(getBaseActivity(), it)
        })
    }

    class YouBikeStationAdapter(private var context: Context, private var list: List<YouBikeStationData>):
        RecyclerView.Adapter<YouBikeStationAdapter.ViewHolder>(){

        inner class ViewHolder(var dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycler, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val binding: ViewDataBinding = holder.dataBinding
            binding.setVariable(BR.item, list[position])
            binding.executePendingBindings()
        }
    }
}