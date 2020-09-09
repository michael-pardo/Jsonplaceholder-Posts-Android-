package com.mistpaag.jsonplaceholder.post.views.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import coil.api.load
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.databinding.ActivityDetailPostBinding
import org.koin.android.ext.android.inject

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding
    private val viewModel by inject<DetailPostViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_post)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val args = intent.extras

        if (args != null) {
            val id = args.getInt("id_post")
            viewModel.fetchPost(id)
        }

        viewModel.fetchUser()

        viewModel.postDetail.observe(this, Observer { post->
            binding.bodyText.text = post.body
            binding.tittleText.text = post.title
            loadFavoriteState(post.favorite)
        })

        binding.backImage.setOnClickListener {
            super.onBackPressed()
        }

        binding.isFavoriteImage.setOnClickListener {
            viewModel.updateFavoritePostStatus()
        }

    }

    private fun loadFavoriteState(favorite:Boolean){
        if (favorite) {
            binding.isFavoriteImage.load(R.drawable.favorite)
        }else{
            binding.isFavoriteImage.load(R.drawable.unfavorite)
        }
    }

}