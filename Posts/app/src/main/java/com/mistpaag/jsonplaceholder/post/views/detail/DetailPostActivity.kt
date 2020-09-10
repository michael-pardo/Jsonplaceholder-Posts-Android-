package com.mistpaag.jsonplaceholder.post.views.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import coil.api.load
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.databinding.ActivityDetailPostBinding
import com.mistpaag.jsonplaceholder.post.utils.Const
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


        viewModel.postDetail.observe(this, Observer { post->
            binding.bodyText.text = post.body
            binding.tittleText.text = post.title
            loadFavoriteState(post.favorite)
            viewModel.fetchUser(post.userId)
        })

        viewModel.user.observe(this, Observer {user->
            with(binding){
                nameText.text = "${resources.getString(R.string.name)} : ${user.name}"
                usernameText.text = "${resources.getString(R.string.username)} : ${user.username}"
                emailText.text = "${resources.getString(R.string.email)} : ${user.email}"
                phoneText.text = "${resources.getString(R.string.phone)} : ${user.phone}"
                websiteText.text = "${resources.getString(R.string.website)} : ${user.website}"

                binding.locationImage.setOnClickListener {
                    goToMaps(user.address.geo.lat, user.address.geo.lng)
                }
            }
        })

        binding.backImage.setOnClickListener {
            super.onBackPressed()
        }

        binding.isFavoriteImage.setOnClickListener {
            viewModel.updateFavoritePostStatus()
        }

    }

    fun goToMaps(lat:String, lng:String){
        val gmmIntentUri = Uri.parse("geo:$lat,$lng")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
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