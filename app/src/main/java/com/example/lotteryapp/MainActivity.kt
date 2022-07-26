package com.example.lotteryapp

import Lotto
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

/*
처음에 프리퍼런스에서 "lottonums" 키를 통해서 문자열 값을 가져오는데
맨 처음에는 저장된게 없으므로 두 번째 인자값인 빈 문자열을 가져옴
val lottoNums = pref.getString("lottonums","")
그리고 이후 if,else 표현식을 통해서 빈 문자열인 경우에는
빈 리스트를 하나 생성하도록 하고 그 리스트에 현재 로또 번호 저장
이후 val lottoNums = pref.getString("lottonums","")
가 실행될 때에는 저장된 번호가 있으므로 "1-2-3-4-5-6" 와 같은 값 리턴
else 쪽으로 빠져서 ["1-2-3-4-5-6"] 내용을 담은 리스트 반환
매번 저장할 때마다 joinToString을 통해서 문자열로 바꿔서 저장하므로
실제 저장되는 내용은
"1-2-3-4-5-6,6-5-4-3-2-1"  <= 이런 문자열이 저장됨
 */

class MainActivity : AppCompatActivity() {
    //lateinit는 null러블 타입을 안 해도 됨. 초기화 하지 않은 대신 무조건 대입을 하고 시작해야 한다.
    private lateinit var currentNums: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nums = findViewById<TextView>(R.id.lotto_num)

        val pref = getSharedPreferences("nums", Context.MODE_PRIVATE)

        val lottoNumView = findViewById<TextView>(R.id.lotto_num)
        currentNums = generateRandomLottoNum(6, "-")
        lottoNumView.text = currentNums

        val generateNumberBtn = findViewById<Button>(R.id.new_num)
        generateNumberBtn.setOnClickListener {
            currentNums = generateRandomLottoNum(6, "-")
            lottoNumView.text = currentNums
        }

        val saveNumberBtn = findViewById<Button>(R.id.save)
        saveNumberBtn.setOnClickListener {
            var lottoNums = pref.getString("lottonums", "")
            var numList = if(lottoNums == ""){
                mutableListOf<String>()
            }else{
                lottoNums!!.split(",").toMutableList()
            }
            numList.add(currentNums)
            Log.d("mytag",numList.size.toString())
            Log.d("mytag",numList.toString())

            val editor = pref.edit()
            editor.putString("lottonums",numList.joinToString(","))
            editor.apply()
        }

        var result = findViewById<Button>(R.id.result)
        result.setOnClickListener {
            var intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://m.dhlottery.co.kr/gameResult.do?method=byWin&wiselog=M_A_1_8")
            )
            startActivity(intent)
        }

        findViewById<Button>(R.id.check).setOnClickListener{
            val intent = Intent(this,LottoList::class.java)
            startActivity(intent)
        }
    }



    fun generateRandomLottoNum(i: Int, s: String): String {
        val nums = mutableListOf<Int>()
        for (i in 1..6) nums.add((1..45).random())
        currentNums = "${nums[0]}-${nums[1]}-${nums[2]}-${nums[3]}-${nums[4]}-${nums[5]}"
        return currentNums
    }
}