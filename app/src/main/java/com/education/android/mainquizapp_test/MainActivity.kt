package com.education.android.mainquizapp_test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), QuestionAdapter.OnItemClickListener {

    private val questionList = generateDummyList(30)
    private val adapter = QuestionAdapter(exampleList = questionList, this)
    private val questionViewModel: QuestionViewModel by lazy {
        ViewModelProvider(this).get(QuestionViewModel::class.java)
    }

    private var questionBank: List<List<Question>>? = listOf(
        listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
        ),
        listOf(
            Question(R.string.question_history, false),
            Question(R.string.question_moscow, true),
            Question(R.string.question_universe, false)
        )
    )

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = questionList[position]
        clickedItem.testName = "Clicked name of the test"

        questionViewModel.testId = position
        adapter.notifyItemChanged(position)

        val intent: Intent = Intent(applicationContext, TestActivity::class.java)
        intent.putExtra("message_key", position.toString())
        startActivity(intent)
    }

    private fun generateDummyList(size: Int): ArrayList<Test> {
        val list = ArrayList<Test>()
        for (index in 0 until size) {
            val drawable = R.drawable.ic_launcher_background
            val item = Test(drawable, "Item $index test",
                index % 3, "Description",
                QuestionViewModel().questionBank[index % 3].questionsList
            )
            list.add(item)
        }

        return list
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putParcelable("R.layout.activity_main",
            recyclerView.layoutManager?.onSaveInstanceState()
        )
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}