package com.mistpaag.jsonplaceholder.post.models.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostResponse(
    val body: String,
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int,
    var favorite: Boolean = false,
    var taken: Boolean = false
)