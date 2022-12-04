package com.example.hangman_meathead

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hangman_meathead.databinding.ActivityLeaderboardBinding

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = ScoreAdapter(
            listOf(
                Score("Shiroi_Okami", "Points: 123000","url1"),
                Score("SonikV2", "Points: 12300","url2"),
                Score("SKAAARRRL", "Points: 1230","url3"),
                Score("Manolo69", "Points: 103","url4"),
                Score("Boji", "Points: 23","url5"),
                Score("Puiuiu", "Points: 13","url6"),
                Score("Ruth1209", "Points: 3","url7")
            )
        )

        binding.exitButtonLeaderboard.setOnClickListener{
            val intentMain = Intent(this@LeaderboardActivity, MainMenuActivity::class.java)
            startActivity(intentMain)

            finish()
        }

    }
}