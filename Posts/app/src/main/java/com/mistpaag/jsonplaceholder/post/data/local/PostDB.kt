package com.mistpaag.jsonplaceholder.post.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mistpaag.jsonplaceholder.post.data.local.dao.PostDao
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse

@Database(entities = [
    PostResponse::class
],version = 1, exportSchema = false)
abstract class PostDB : RoomDatabase(){
    abstract val postdao: PostDao
}