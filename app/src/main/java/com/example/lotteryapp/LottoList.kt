package com.example.lotteryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LottoList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto_list)
        val pref = getSharedPreferences("nums", Context.MODE_PRIVATE)
        var lottoNums = pref.getString("lottonums", "")
        //준비물1 => numList
        var numList = if(lottoNums == ""){
            mutableListOf<String>()
        }else {
            lottoNums!!.split(",").toMutableList()
        }

        var listview = findViewById<RecyclerView>(R.id.num_list)
        listview.setHasFixedSize(true)
        //준비물3 => 레이아웃 매니저
        listview.layoutManager = LinearLayoutManager(this)

        //준비물4,5
        listview.adapter = LottolistAdapter(numList)

        listview.setHasFixedSize(true)
    }
}
