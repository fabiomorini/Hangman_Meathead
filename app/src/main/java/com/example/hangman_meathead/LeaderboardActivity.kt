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
                Score("Name1", "123000","url1"),
                Score("Name2", "12300","url2"),
                Score("Name3", "1230","url3"),
                Score("Name4", "103","url4"),
                Score("Name5", "23","url5"),
                Score("Name6", "13","url6"),
                Score("Name7", "3","url7")
            )
        )
    }
}