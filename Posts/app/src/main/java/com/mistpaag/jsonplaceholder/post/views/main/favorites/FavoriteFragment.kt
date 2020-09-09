package com.mistpaag.jsonplaceholder.post.views.main.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.adapters.PostAdapter
import com.mistpaag.jsonplaceholder.post.databinding.FavoriteFragmentBinding
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.views.main.all.PostListFragmentDirections
import org.koin.android.ext.android.inject

class FavoriteFragment() : Fragment() {

    companion object {
        fun newInstance() =
            FavoriteFragment()
    }

    private lateinit var binding : FavoriteFragmentBinding

    private val viewModel by inject<FavoriteViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = PostAdapter{
            if (it.clickOnImage){

            }else{
                goToDetail(it.postId)
            }
        }

        binding.favoriteRecycler.layoutManager = GridLayoutManager(context, 1)

        binding.favoriteRecycler.adapter = adapter

        viewModel.fetchPosts()

        viewModel.favoritePostList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toMutableList())
        })

        return binding.root
    }

    private fun goToDetail(favoritePostId: Int) {
        findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToDetailPostActivity())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}