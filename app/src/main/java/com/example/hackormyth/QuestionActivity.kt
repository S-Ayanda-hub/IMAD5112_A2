package com.example.hackormyth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class QuestionActivity : AppCompatActivity() {

    private lateinit var flashcard: TextView
    private lateinit var feedback: TextView
    private lateinit var questionNumber: TextView
    private lateinit var btnHack: Button
    private lateinit var btnMyth: Button
    private lateinit var btnNext: Button

    private val questions = arrayOf(
        "Drinking water first thing boosts metabolism",
        "Cracking knuckles causes arthritis",
        "Studying in short bursts improves memory",
        "You only use 10% of your brain",
        "Sleeping 8 hours improves focus"
    )

    // true = Hack, false = Myth
    private val answers = arrayOf(true, false, true, false, true)

    private var currentIndex = 0
    private var score = 0
    private var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)

        flashcard = findViewById(R.id.flashcard)
        feedback = findViewById(R.id.feedback)
        btnHack = findViewById(R.id.btnHack)
        btnMyth = findViewById(R.id.btnMyth)
        btnNext = findViewById(R.id.btnNext)


        loadQuestion()

        btnHack.setOnClickListener {
            if (!answered) checkAnswer(true)
        }

        btnMyth.setOnClickListener {
            if (!answered) checkAnswer(false)
        }

        btnNext.setOnClickListener {
            currentIndex++

            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                goToScoreScreen()
            }
        }
    }

    private fun loadQuestion() {
        flashcard.text = questions[currentIndex]
        feedback.text = ""
        answered = false

        questionNumber.text = "Question ${currentIndex + 1}/${questions.size}"

        btnHack.isEnabled = true
        btnMyth.isEnabled = true
    }

    private fun checkAnswer(userAnswer: Boolean) {
        answered = true

        val correctAnswer = answers[currentIndex]

        if (userAnswer == correctAnswer) {
            feedback.text = "Correct! That's a real time-saver!"
            score++
        } else {
            val correctText = if (correctAnswer) "Hack" else "Myth"
            feedback.text = "Wrong! You’ve got to improve your knowledge!"
        }

        btnHack.isEnabled = false
        btnMyth.isEnabled = false
    }

    private fun goToScoreScreen() {

        var totalQuestions = 0
        for (q in questions) {
            totalQuestions++
        }

        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", totalQuestions)
        startActivity(intent)
    }
}