package com.example.mbti_prac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MbtiResultActivity : AppCompatActivity() {
    lateinit var resultMbti: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mbti_result)

        val btn_restart = findViewById<Button>(R.id.btn_restart)
        btn_restart.setOnClickListener {
            SurveyMbtiResult.result = mutableListOf()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        val resultList = listOf(
            listOf("E", "I"),
            listOf("N", "S"),
            listOf("T", "F"),
            listOf("J", "P")
        )

        val tv_result = findViewById<TextView>(R.id.tv_result)
        var getMbtiResult: List<Int>? = listOf(0, 0, 0, 0)
        getMbtiResult = intent.getIntegerArrayListExtra("questionResult")
        Toast.makeText(this, "${getMbtiResult}", Toast.LENGTH_LONG).show()
        var result: String = ""
        getMbtiResult?.forEachIndexed { index, value ->
            result += resultList[index][value]
        }
        tv_result.text = result
    }

//    fun resultMbtiGet(resultList: List<List<String>>, resultGetList : ArrayList<Int>): String{
//
//        return result
//    }
}