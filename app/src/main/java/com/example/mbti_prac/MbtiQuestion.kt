package com.example.mbti_prac

data class MbtiQuestion (
    val question: String,
    val option1: String,
    val option2: String,
    var cnt1: Int,
    var cnt2: Int
        )