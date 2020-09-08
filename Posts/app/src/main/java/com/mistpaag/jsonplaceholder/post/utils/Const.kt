package com.mistpaag.jsonplaceholder.post.utils

object Const {
    val dbName = "posts.db"

    const val URL_BASE = "https://jsonplaceholder.typicode.com"


    fun Log(message:Any){
        android.util.Log.d("lol", message.toString())
    }
}