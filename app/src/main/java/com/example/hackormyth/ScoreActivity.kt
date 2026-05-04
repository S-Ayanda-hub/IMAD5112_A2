package com.example.hackormyth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var totalFeedback: TextView
    private lateinit var reviewsText: TextView
    private lateinit var btnReplay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        resultText = findViewById(R.id.resultText)
        totalFeedback = findViewById(R.id.totalFeedback)
        reviewsText = findViewById(R.id.Reviews)
        btnReplay = findViewById(R.id.btnReplay)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        resultText.text = getString(R.string.your_score, score, total)

        val percentage = if (total > 0) (score * 100) / total else 0

        totalFeedback.text = when {
            percentage >= 50 -> " Great job! Master Hacker!"
            else -> " Stay safe online and Keep practising!"
        }

        val reviewMessages = arrayOf(
            "Hydration improves body function",
            "Knuckle cracking does NOT cause arthritis",
            "Short study sessions boost memory",
            "You use more than 10% of your brain",
            "Sleep improves focus and performance"
        )

        var reviewText = "Key Facts:\n"
        for (fact in reviewMessages) {
            reviewText += "• $fact\n"
        }

        reviewsText.text = reviewText

        btnReplay.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}