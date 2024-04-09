package com.example.mbti_prac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_main_start = findViewById<Button>(R.id.btn_main_start)

        btn_main_start.setOnClickListener{
            val intent = Intent(this, MbtiSurveyActivity::class.java)
            startActivity(intent)
        }
    }
}