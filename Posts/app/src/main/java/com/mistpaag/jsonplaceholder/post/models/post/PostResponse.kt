package com.mistpaag.jsonplaceholder.post.models.post

data class PostResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    val favorite: Boolean = false
)