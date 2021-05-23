package com.education.android.mainquizapp_test

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlin.math.abs

private const val message = "Answer has been submitted!"
private const val KEY_INDEX = "index"

class TestActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var questionText: TextView
    private lateinit var questionTextView: TextView
    private lateinit var correctAnswers: TextView
    private lateinit var receivedText: TextView

    private var currentCorrectAnswers: Int = 0
    private var position: Int = 0

    private val questionViewModel: QuestionViewModel by lazy {
        ViewModelProvider(this).get(QuestionViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

//        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        questionText = findViewById(R.id.question_text)
        questionTextView = findViewById(R.id.result_text_1)
        correctAnswers = findViewById(R.id.correct_results)
        receivedText = findViewById(R.id.received_text)

        val questionTextResId = questionViewModel.currentQuestionText
        if (questionTextResId != null) {
            questionText.setText(questionTextResId)
        }

        val string: String? = intent.getStringExtra("message_key")
        receivedText.text = string
        if (string != null) {
            position = string.toInt()
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
            updateQuestion()
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
            updateQuestion()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionViewModel.currentQuestionAnswer
        currentCorrectAnswers = if (correctAnswer == userAnswer) {
            currentCorrectAnswers + 1
        } else {
            currentCorrectAnswers
        }

        correctAnswers.text = "Correct answers: $currentCorrectAnswers"
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestion() {
        if (questionViewModel.currentIndex < (questionViewModel.questionBank[position].questionsList?.size ?: 1) - 1) {
            val questionTextResId = questionViewModel.currentQuestionText

            questionViewModel.moveNext()
            if (questionTextResId != null) {
                questionText.setText(questionTextResId)
            }
        } else {
            trueButton.apply {
                trueButton.text = "Go back"
            }
            trueButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            falseButton.apply {
                falseButton.text = "Restart"
                isEnabled = false
            }

            val testId = 0
            questionText.text =
                "Your result: " + "${currentCorrectAnswers * 100 / questionViewModel.questionBank[testId].questionsList?.size!!}"

            questionTextView.visibility = View.VISIBLE
            questionTextView.text = questionViewModel.setTextResult(currentCorrectAnswers)

            correctAnswers.visibility = View.GONE
        }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, TestActivity::class.java)
        }
    }
}