package com.example.lotteryapp

import Lotto
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class LottoAdapter(private val dataList: List<Lotto>):
    RecyclerView.Adapter<LottoAdapter.lottoItemViewHolder>()
{
    class lottoItemViewHolder(val view : View ) : RecyclerView.ViewHolder(view){  //view를 홀더하는 것을 본질적인 역할이다.
        lateinit var lotto: Lotto
        val lottoText = view.findViewById<TextView>(R.id.check) //타입 : TextView
        val lottoFrom = view.findViewById<TextView>(R.id.check) //타입 : TextView
        val shareBtn = view.findViewById<Button>(R.id.check)

        init {
            shareBtn.setOnClickListener{
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TITLE,"힘이 되는 명언")
                intent.putExtra(Intent.EXTRA_SUBJECT,"힘이 되는 명언")
                intent.putExtra(Intent.EXTRA_TEXT,"${lotto.text}\n 출처 : ${lotto.from}")
                intent.type = "text/plain"
                val chooser = Intent.createChooser(intent,"명언 공유")
                it.context.startActivity(chooser)
            }
        }

        fun bind(q: Lotto){ //명언 데이터와 view를 결속을 시킬 것이다.
            this.lotto = q

            lottoText.text = lotto.text
            lottoFrom.text = lotto.from
        }

    }

    //식별자 veiwGroup를 받은 후 quote_list_item의 내용을 받아오는 것
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): lottoItemViewHolder {
        val view = LayoutInflater.from(parent.context) //parent 뷰그룹으로 리사이클러뷰이다.
            .inflate(viewType, parent, false) //viewType는 XML, parent리싸이클러뷰에 붙일 것이다 .

        return lottoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: lottoItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
    override fun getItemCount() = dataList.size
    override fun getItemViewType(position: Int) = R.layout.activity_lotto_adapter

}