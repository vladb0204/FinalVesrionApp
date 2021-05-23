package com.education.android.mainquizapp_test

import androidx.lifecycle.ViewModel
import kotlin.math.*

class QuestionViewModel : ViewModel() {

    private val resultsArray = listOf(
        "Bad", "Good", "Very well!", "Perfect"
    )

    var questionBank = listOf(
        Test(R.drawable.ic_launcher_background,
            "First test",
            0,
            "This test was created just for fun",
            listOf(
                Question(R.string.question_australia, true),
                Question(R.string.question_oceans, true),
                Question(R.string.question_mideast, false),
                Question(R.string.question_africa, false),
                Question(R.string.question_americas, true),
                Question(R.string.question_asia, true)
            )
        ),
        Test(R.drawable.ic_launcher_foreground,
            "Second test",
            1,
            "This test all is created!",
            listOf(
                Question(R.string.question_history, false),
                Question(R.string.question_moscow, true),
                Question(R.string.question_universe, true),
                Question(R.string.question_programming, true),
                Question(R.string.question_wonders, true),
                Question(R.string.question_border, true),
            )
        ),
        Test(R.drawable.ic_launcher_background,
            "Third test",
            2,
            "This test has also been created!",
            listOf(
                Question(R.string.question_gagarin, true),
                Question(R.string.question_spain, false),
                Question(R.string.question_germany, true)
            )
        )
    )

    var currentIndex: Int = 0
    var correctAnswers: Int = 0
    var testId: Int = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[testId].questionsList?.get(abs(currentIndex))?.answer == true

    val currentQuestionText: Int?
        get() = questionBank[testId].questionsList?.get(abs(currentIndex))?.textResId

    fun moveNext() {
        currentIndex++;
    }

    fun movePrev() {
        currentIndex = when (currentIndex) {
            0 -> 0
            else -> currentIndex - 1
        }
    }

    fun getAnswersCount(): Int {
        return this.correctAnswers
    }

    fun getQuestionsBankSize(): Int {
        return this.questionBank.size
    }

    fun getTestsQuestion(index: Int): List<Question>? {
        return this.questionBank[index].questionsList
    }

    fun setTextResult(num: Int): String {
        return when ((num * 100 /
                (this.questionBank[testId].questionsList?.size ?: 1))) {
            in 0..30 -> resultsArray[0]
            in 31..50 -> resultsArray[1]
            in 51..70 -> resultsArray[2]
            in 70..100 -> resultsArray[3]
            else -> "Nothing"
        }
    }

    fun setResultTextPercent(): String {
        val percentage = (this.correctAnswers * 100 /
                (this.questionBank[testId].questionsList?.size ?: 1))
        return "Your result $percentage"
    }
}