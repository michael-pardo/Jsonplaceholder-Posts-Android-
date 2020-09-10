package com.mistpaag.jsonplaceholder.post.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.databinding.ActivityMainBinding
import com.mistpaag.jsonplaceholder.post.utils.Const
import com.mistpaag.jsonplaceholder.post.views.SharedViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var lastMenuId = 0

    private val sharedViewModel by inject<SharedViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val navController = findNavController(R.id.nav_host)
        binding.bottomNav.setOnNavigationItemSelectedListener {
            val menuId = it.itemId
            if (lastMenuId == menuId) return@setOnNavigationItemSelectedListener true
            lastMenuId = menuId
            when(lastMenuId){
                R.id.postListFragment -> {
                    navController.navigate(R.id.postListFragment)
                    hideOptions()
                }
                else -> {
                    navController.navigate(R.id.favoriteFragment)
                    hideOptions()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        binding.clearPostsImage.setOnClickListener {
            sharedViewModel.clearPosts(true)
        }

        binding.reloadPostsImage.setOnClickListener {
            sharedViewModel.needRemoteData(true)
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.titleToolbar.text = destination.label
        }

    }

    fun hideOptions(){
        binding.optionsConstraint.isVisible = !binding.optionsConstraint.isVisible
    }
}



