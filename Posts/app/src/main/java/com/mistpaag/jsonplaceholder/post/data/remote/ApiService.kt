package com.mistpaag.jsonplaceholder.post.data.remote

import com.mistpaag.jsonplaceholder.post.models.PostResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/posts")
    fun fetchPosts(
    ): Deferred<List<PostResponse>>
}