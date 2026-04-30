package com.example.hackormyth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        class QuestionActivity : AppCompatActivity() {

            private lateinit var flashcard: TextView
            private lateinit var feedback: TextView
            private lateinit var btnHack: Button
            private lateinit var btnMyth: Button
            private lateinit var btnNext: Button

            private var currentIndex = 0
            private var score = 0
            private var answered = false

            private val questions = arrayOf(
                "Putting your phone in rice fixes water damage",
                "Drinking water improves concentration",
                "Cracking knuckles causes arthritis"
            )

            private val answers = arrayOf(
                false, // Myth
                true,  // Hack
                false  // Myth
            )

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_question)

                flashcard = findViewById(R.id.flashcard)
                feedback = findViewById(R.id.feedback)
                btnHack = findViewById(R.id.btnHack)
                btnMyth = findViewById(R.id.btnMyth)
                btnNext = findViewById(R.id.btnNext)

                loadQuestion()

                btnHack.setOnClickListener {
                    checkAnswer(true)
                }

                btnMyth.setOnClickListener {
                    checkAnswer(false)
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
            }

            private fun checkAnswer(userAnswer: Boolean) {
                if (answered) return

                val correctAnswer = answers[currentIndex]

                if (userAnswer == correctAnswer) {
                    feedback.text = "Correct! 🎉"
                    score++
                } else {
                    feedback.text = "Wrong! ❌"
                }

                answered = true
            }

            private fun goToScoreScreen() {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()

            }

        }
    }
}