package com.mistpaag.jsonplaceholder.post.data.repository

import android.content.Context
import com.mistpaag.jsonplaceholder.post.data.local.dao.PostDao
import com.mistpaag.jsonplaceholder.post.data.remote.ApiService
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.models.user.User
import com.mistpaag.jsonplaceholder.post.utils.Const
import com.mistpaag.jsonplaceholder.post.utils.haveConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class Repository(private val remoteData: ApiService, private val localData: PostDao, private val context: Context){
    suspend fun fetchPosts() = flow<List<PostResponse>> {
        try {
            if (context.haveConnection()){
                fetchLocalPosts().collect {
                    if (it.isNotEmpty()) {
                        emit(it)
                    }else{
                        val response = remoteData.fetchPosts().await()
                        localData.insertPosts(response)
                        fetchLocalPosts().collect {
                            emit(it)
                        }
                    }
                }

            }else{
                fetchLocalPosts().collect {
                    emit(it)
                }
            }
        }catch (t:Throwable){
            fetchLocalPosts().collect {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun fetchLocalPosts() = flow{
        emit(localData.fetchPosts())
    }.flowOn(Dispatchers.IO)

    fun fetchLocalFavoritesPosts() = flow{
        emit(localData.fetchFavoritesPosts())
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

    suspend fun updateFavoritePostStatus(id:Int, isFavorite:Boolean){
        withContext(Dispatchers.IO) {
            localData.updateFavoritePost(id, isFavorite)
        }
    }

    fun fetchLocalPost(id:Int) = flow{
        emit(localData.fetchPost(id))
    }.flowOn(Dispatchers.IO)


}