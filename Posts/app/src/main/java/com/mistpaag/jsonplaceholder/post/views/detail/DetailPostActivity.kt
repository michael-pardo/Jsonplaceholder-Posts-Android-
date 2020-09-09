package com.mistpaag.jsonplaceholder.post.views.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
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

        viewModel.fetchUser()

        binding.backImage.setOnClickListener {
            super.onBackPressed()
        }

    }
}