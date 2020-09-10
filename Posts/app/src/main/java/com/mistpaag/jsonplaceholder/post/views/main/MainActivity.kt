package com.mistpaag.jsonplaceholder.post.views.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.databinding.ActivityMainBinding
import com.mistpaag.jsonplaceholder.post.utils.Const

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var lastMenuId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val navController = findNavController(R.id.nav_host)
        binding.bottomNav.setOnNavigationItemSelectedListener {
            val menuId = it.itemId
            if (lastMenuId == menuId) return@setOnNavigationItemSelectedListener true
            lastMenuId = menuId
            when(lastMenuId){
//                R.id.postListFragment -> navController.popBackStack(R.id.postListFragment, false)
                R.id.postListFragment -> navController.navigate(R.id.postListFragment)
                else -> navController.navigate(R.id.favoriteFragment)
            }
            return@setOnNavigationItemSelectedListener true
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.titleToolbar.text = destination.label
        }

    }
}



