package com.mistpaag.jsonplaceholder.post.views.main.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.jsonplaceholder.post.data.repository.Repository
import com.mistpaag.jsonplaceholder.post.models.PostResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: Repository) : ViewModel() {
    // TODO: Implement the ViewModel

    val favoritePostList : LiveData<List<PostResponse>>
        get()= _favoritePostList
    private val _favoritePostList = MutableLiveData<List<PostResponse>>()

    fun fetchPosts(){
        viewModelScope.launch {
            repository.fetchPosts().collect { posts ->
                _favoritePostList.value = posts
                posts.forEach {

                }

            }
        }

    }
}