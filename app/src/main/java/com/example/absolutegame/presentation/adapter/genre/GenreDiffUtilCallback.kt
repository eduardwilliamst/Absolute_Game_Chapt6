package com.example.absolutegame.presentation.adapter.genre

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.Genre

class GenreDiffUtilCallback : DiffUtil.ItemCallback<com.example.domain.Genre>() {
    override fun areItemsTheSame(oldItem: com.example.domain.Genre, newItem: com.example.domain.Genre): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: com.example.domain.Genre, newItem: com.example.domain.Genre): Boolean {
        return oldItem.id == newItem.id
    }
}