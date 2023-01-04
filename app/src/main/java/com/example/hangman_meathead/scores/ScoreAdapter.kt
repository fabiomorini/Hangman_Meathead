package com.example.hangman_meathead.scores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hangman_meathead.databinding.ViewScoreAdapterBinding

class ScoreAdapter (private val scores : List<Score>) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ViewScoreAdapterBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(score : Score) {
            binding.scoreName.text = score.name
            binding.scorePoints.text = score.points
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewScoreAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(scores[position])
    }

    override fun getItemCount() = scores.size
}