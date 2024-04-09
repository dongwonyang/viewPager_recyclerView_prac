package com.example.mbti_prac

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SurveyRecyclerViewAdapter(private val dataList: List<MbtiQuestion>) :
    RecyclerView.Adapter<SurveyRecyclerViewAdapter.ViewHolder>() {

    // ViewHolder 클래스 정의
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_question = itemView.findViewById<TextView>(R.id.tv_survey_question)
        val rb_btn1 = itemView.findViewById<RadioButton>(R.id.rb_survey_question_option1)
        val rb_btn2 = itemView.findViewById<RadioButton>(R.id.rb_survey_question_option2)
        val radioGroup: RadioGroup = itemView.findViewById(R.id.rg_survey_question_option_group)

        fun bind(item: MbtiQuestion){
            tv_question.text = item.question
            rb_btn1.text = item.option1
            rb_btn2.text = item.option2

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    R.id.rb_survey_question_option1 -> {
                        item.cnt1 = 1
                        if(item.cnt2 != 0) item.cnt2 = 0
                    }
                    R.id.rb_survey_question_option2 -> {
                        item.cnt2 = 1
                        if(item.cnt1 != 0) item.cnt1 = 0
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_survey_option, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder: ViewHolder에 데이터 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    // getItemCount: 데이터 개수 반환
    override fun getItemCount(): Int {
        return dataList.size
    }
}