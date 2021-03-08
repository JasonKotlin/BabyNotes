package com.jason.babynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.jason.babynotes.view.MVVMActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var showTime: TextView = findViewById(R.id.show_time)

        val getTime: Button = findViewById(R.id.getTime)
        getTime.setOnClickListener(View.OnClickListener {
            showTime.text = getNow()
        })

        val clear: Button = findViewById(R.id.clear)
        clear.setOnClickListener(View.OnClickListener {
            showTime.text = "yyyy-MM-dd HH:mm:ss"
        })

        val toNext: Button = findViewById(R.id.next_page)
        toNext.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(this, MVVMActivity::class.java).apply {
                //如果要傳值就在這裡面處理
                //TODO 3/8 David 說有哪幾種要記得？
            }
            startActivity(intent)
        })
    }

    fun getNow(): String {
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        } else {
            var tms = Calendar.getInstance()
            return tms.get(Calendar.YEAR).toString() + "-" +
                    tms.get(Calendar.MONTH).toString() + "-" +
                    tms.get(Calendar.DAY_OF_MONTH).toString() + " " +
                    tms.get(Calendar.HOUR_OF_DAY).toString() + ":" +
                    tms.get(Calendar.MINUTE).toString() + ":" +
                    tms.get(Calendar.SECOND).toString()
        }
    }
}