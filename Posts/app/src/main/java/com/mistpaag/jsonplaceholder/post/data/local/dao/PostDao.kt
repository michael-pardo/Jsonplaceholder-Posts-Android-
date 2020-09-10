package com.mistpaag.jsonplaceholder.post.data.local.dao

import androidx.room.*
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.models.user.User

@Dao
interface PostDao{

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListPosts(posts: List<PostResponse>)

    @Transaction
    fun insertPosts(posts: List<PostResponse>){
        for (i in 0 until 20) {
            posts[i].taken = true
        }
        insertListPosts(posts)
    }

    @Query("SELECT * FROM posts")
    fun fetchPosts(): List<PostResponse>

    @Query("SELECT * FROM posts WHERE id=:id")
    fun fetchPost(id:Int):PostResponse

    @Query("SELECT * FROM posts WHERE favorite=1")
    fun fetchFavoritesPosts(): List<PostResponse>

    @Query("UPDATE posts SET favorite=:isFavorite WHERE id=:id")
    fun updateFavoritePost(id:Int, isFavorite:Boolean)

    @Query("UPDATE posts set taken=1 WHERE id IN(SELECT id from posts LIMIT 20)")
    fun updateBlueIndicatorPosts()

    @Query("UPDATE posts set taken=0 WHERE id=:id")
    fun takenPost(id:Int)

    @Query("DELETE FROM posts WHERE id=:id")
    fun deletePost(id:Int)

    @Query("DELETE FROM posts")
    fun deletePosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id=:id")
    fun fetchUser(id:Int):User

}