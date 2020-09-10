package com.mistpaag.jsonplaceholder.post.views.main.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.jsonplaceholder.post.data.repository.Repository
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.FieldPosition

class PostListViewModel(private val repository: Repository) : ViewModel() {

    val postList : LiveData<List<PostResponse>>
        get()= _postList
    private val _postList = MutableLiveData<List<PostResponse>>()

    fun fetchPosts(){
        viewModelScope.launch {
            repository.fetchPosts().collect { posts ->
                _postList.value = posts
            }
        }
    }

    fun fetchLocalPosts(){
        viewModelScope.launch {
            repository.fetchLocalPosts().collect { posts ->
                _postList.value = posts
            }
        }
    }

    fun deleteItem(id: Int){
        viewModelScope.launch {
            repository.deletePost(id)
        }
    }

    fun updateFavoritePostStatus(id:Int, isFavorite:Boolean){
        viewModelScope.launch {
            repository.updateFavoritePostStatus(id, isFavorite)
            repository.fetchLocalPosts()
        }
    }
}