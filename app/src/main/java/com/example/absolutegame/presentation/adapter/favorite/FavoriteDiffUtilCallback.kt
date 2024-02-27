package com.example.absolutegame.presentation.adapter.favorite

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.Game

class FavoriteDiffUtilCallback : DiffUtil.ItemCallback<com.example.domain.Game>() {
    override fun areItemsTheSame(oldItem: com.example.domain.Game, newItem: com.example.domain.Game): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: com.example.domain.Game, newItem: com.example.domain.Game): Boolean {
        return oldItem.id == newItem.id
    }
}