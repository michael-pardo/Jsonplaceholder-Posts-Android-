package com.mistpaag.jsonplaceholder.post.data.remote

import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.models.user.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/posts")
    fun fetchPosts(
    ): Deferred<List<PostResponse>>

    @GET("/users/{id}")
    fun fetchUser(
        @Path("id") id:Int
    ): Deferred<User>
}