package com.example.linah_alkhurayyif_parsinglocaljsonfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var dataList:ArrayList<Data>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataList= arrayListOf()
        val inputStream= assets.open("data.json")
        val data= ByteArray(inputStream.available())
        inputStream.read(data)
        inputStream.close()

        val jsonArray= JSONArray(String(data))
        for (i in 0 until jsonArray.length()) {
            val jsonObject = JSONObject(jsonArray[i].toString())
            dataList.add(Data(jsonObject.getString("title"), jsonObject.getString("url")))
        }


      recyclerView.adapter= RVAdapter(this,dataList)
       recyclerView.layoutManager= LinearLayoutManager(this)

    }
}