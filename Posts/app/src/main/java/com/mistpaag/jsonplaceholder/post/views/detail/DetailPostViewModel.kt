package com.mistpaag.jsonplaceholder.post.views.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistpaag.jsonplaceholder.post.data.repository.Repository
import com.mistpaag.jsonplaceholder.post.utils.Const
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailPostViewModel(private val repository: Repository): ViewModel(){

    fun fetchUser(){
        viewModelScope.launch {
            repository.fetchUser().collect {
                it?.let {
                    Const.Log(it)
                }
            }
        }
    }

}