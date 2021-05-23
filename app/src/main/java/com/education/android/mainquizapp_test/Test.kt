package com.education.android.mainquizapp_test

data class Test(
    val imageResource: Int, var testName: String, val testId: Int,
    val description: String, val questionsList: List<Question>
)