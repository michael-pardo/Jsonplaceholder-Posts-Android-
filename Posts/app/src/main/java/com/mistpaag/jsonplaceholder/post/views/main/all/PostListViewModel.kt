package com.mistpaag.jsonplaceholder.post.views.main.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.jsonplaceholder.post.data.repository.Repository
import com.mistpaag.jsonplaceholder.post.models.PostResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostListViewModel(private val repository: Repository) : ViewModel() {

    val postList : LiveData<List<PostResponse>>
        get()= _postList
    private val _postList = MutableLiveData<List<PostResponse>>()

    fun fetchPosts(){
        viewModelScope.launch {
            repository.fetchPosts().collect { posts ->
                _postList.value = posts
                posts.forEach {

                }

            }
        }

    }
}