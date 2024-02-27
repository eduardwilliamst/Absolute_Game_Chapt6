package com.example.datas.remote.response

import com.example.domain.Genre
import com.google.gson.annotations.SerializedName

data class GenreResult(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("games_count")
    val gamesCount: Int?,
    @SerializedName("image_background")
    val imageBackground: String?
)

fun GenreResult.toGenre(): com.example.domain.Genre {
    return com.example.domain.Genre(
        name = name.orEmpty(),
        id = id ?: -1,
        slug = slug.orEmpty(),
        gamesCount = gamesCount ?: -1,
        imageBackground = imageBackground.orEmpty()
    )
}