package com.education.android.mainquizapp_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BarActivity : AppCompatActivity() {

    private lateinit var goBackButton: Button
    private lateinit var startTestButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar)
    }
}