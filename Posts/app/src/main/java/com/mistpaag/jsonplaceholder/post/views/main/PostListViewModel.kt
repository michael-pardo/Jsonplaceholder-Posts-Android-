package com.mistpaag.jsonplaceholder.post.views.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.jsonplaceholder.post.data.repository.Repository
import com.mistpaag.jsonplaceholder.post.utils.Const
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostListViewModel(private val repository: Repository) : ViewModel() {

    fun fetchPosts(){
        viewModelScope.launch {
            repository.fetchPosts().collect { posts ->
                posts.forEach {
                    Const.Log(it)
                }

            }
        }

    }
}