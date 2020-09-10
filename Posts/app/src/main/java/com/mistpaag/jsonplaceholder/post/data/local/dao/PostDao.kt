package com.mistpaag.jsonplaceholder.post.data.local.dao

import androidx.room.*
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse

@Dao
interface PostDao{

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPosts(posts: List<PostResponse>)

    @Query("SELECT * FROM posts")
    fun fetchPosts(): List<PostResponse>

    @Query("SELECT * FROM posts WHERE id=:id")
    fun fetchPost(id:Int):PostResponse

    @Query("SELECT * FROM posts WHERE favorite=1")
    fun fetchFavoritesPosts(): List<PostResponse>

    @Query("UPDATE posts SET favorite=:isFavorite WHERE id=:id")
    fun updateFavoritePost(id:Int, isFavorite:Boolean)

    @Query("DELETE FROM posts WHERE id=:id")
    fun deletePost(id:Int)

    @Query("DELETE FROM posts")
    fun deletePosts()

}