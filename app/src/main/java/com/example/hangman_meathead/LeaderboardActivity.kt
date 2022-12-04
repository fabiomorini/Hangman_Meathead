package com.example.hangman_meathead

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LeaderboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

class CustomRecyclerViewAdapter(private val elements: List<Element>) :

    RecyclerView.Adapter<CustomRecyclerViewAdapter.ElementViewHolder>() {

    inner class ElementViewHolder(binding: ListElementBinding) : RecyclerView.ViewHolder(binding.root) {
        //set the variables for our element
        val name = binding.name
        val image = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListParticleBinding.inflate(layoutInflater, parent, false)
        return ParticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val element = elements[position]

        // Set name and image
        holder.name.text = element.name
        holder.image.drawable = element.image
    }


    override fun getItemCount(): Int = elements.size


}
