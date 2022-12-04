package com.example.hangman_meathead

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
                Score("Name1", "url1"),
                Score("Name2", "url12"),
                Score("Name3", "url13"),
                Score("Name4", "url14"),
                Score("Name5", "url15"),
                Score("Name6", "url16"),
                Score("Name7", "url17")
            )
        )
    }
}