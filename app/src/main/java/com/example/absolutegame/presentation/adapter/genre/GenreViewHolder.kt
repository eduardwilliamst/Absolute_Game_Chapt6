package com.example.absolutegame.presentation.adapter.genre

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.absolutegame.databinding.ItemGenreBinding
import com.example.domain.Genre

class GenreViewHolder(
    private val binding: ItemGenreBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: com.example.domain.Genre) {
        binding.headingText.text = genre.name
        binding.backgroundImage.load(genre.imageBackground)
    }
}