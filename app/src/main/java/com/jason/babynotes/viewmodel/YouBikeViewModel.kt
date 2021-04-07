package com.jason.babynotes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jason.babynotes.model.YouBikeStationData
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL
import java.util.*
import kotlin.concurrent.timerTask

class YouBikeViewModel: ViewModel() {
    var stationList = MutableLiveData<List<YouBikeStationData>>()
    val delay = 30 * 1000L

    fun startRetrieveData() {
        Timer().schedule(timerTask{
            getBikeDataFromServer()
        }, 0, delay)
    }
    private fun getBikeDataFromServer() {
        doAsync {
            val tempList = mutableListOf<YouBikeStationData>()
            val url = "https://tcgbusfs.blob.core.windows.net/blobyoubike/YouBikeTP.json"
            val jsonStr = URL(url).readText()
            val jsonObj = JSONObject(jsonStr).getJSONObject("retVal")
            jsonObj.keys().forEach {
                val stationObj = jsonObj.getJSONObject(it)
                tempList.add(
                    YouBikeStationData(
                        stationObj.getString("sno"),
                        stationObj.getString("sna"),
                        stationObj.getString("tot"),
                        stationObj.getString("sbi"),
                        stationObj.getString("sarea"),
                        stationObj.getString("mday"),
                        stationObj.getString("lat"),
                        stationObj.getString("lng"),
                        stationObj.getString("ar"),
                        stationObj.getString("sareaen"),
                        stationObj.getString("snaen"),
                        stationObj.getString("aren"),
                        stationObj.getString("bemp"),
                        stationObj.getString("act")
                    )
                )
            }
            stationList.postValue(tempList)
        }
    }
}
