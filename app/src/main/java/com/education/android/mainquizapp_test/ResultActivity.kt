package com.education.android.mainquizapp_test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class ResultActivity: AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var resultPercentText: TextView
    private lateinit var restartButton: Button
    private lateinit var goBackButton: Button

     
//    private val questionViewModel: QuestionViewModel by lazy {
//        // Deprecated
//        ViewModelProvider(this).get(QuestionViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultText = findViewById(R.id.result_text)
        resultPercentText = findViewById(R.id.result_percent_text)
        restartButton = findViewById(R.id.restart_button)
        goBackButton = findViewById(R.id.go_back_button)

//        resultText.text = questionViewModel.setTextResult(0)
//        resultPercentText.text = questionViewModel.setResultTextPercent()

        restartButton.setOnClickListener {
            Toast.makeText(this,
                "Oops! This button is not working at the moment!", Toast.LENGTH_SHORT).show()
        }
        goBackButton.setOnClickListener {
            startActivity(MainActivity.newIntent(this))
        }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, ResultActivity::class.java)
        }
    }
}