package com.mistpaag.jsonplaceholder.post.data.repository

import android.content.Context
import com.mistpaag.jsonplaceholder.post.data.remote.ApiService
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.models.user.User
import com.mistpaag.jsonplaceholder.post.utils.haveConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository(private val remoteData: ApiService, private val context: Context){
    suspend fun fetchPosts() = flow<List<PostResponse>> {
        try {
            if (context.haveConnection()){
                val response = remoteData.fetchPosts().await()
                emit(response)
            }else{
                emit(emptyList())
            }
        }catch (t:Throwable){
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun fetchUser() = flow<User?> {
        try {
            if (context.haveConnection()){
                val response = remoteData.fetchUser(1).await()
                emit(response)
            }else{
                emit(null)
            }
        }catch (t:Throwable){
            emit(null)
        }
    }.flowOn(Dispatchers.IO)
}