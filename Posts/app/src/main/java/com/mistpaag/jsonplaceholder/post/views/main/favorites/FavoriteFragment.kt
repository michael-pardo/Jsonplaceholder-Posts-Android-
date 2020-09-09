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
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = PostAdapter{
            if (it.clickOnImage){
                viewModel.updateFavoritePostStatus(it.postId, it.isFavorite)
            }else{
                goToDetail(it.postId)
            }
        }

        binding.favoriteRecycler.layoutManager = GridLayoutManager(context, 1)

        binding.favoriteRecycler.adapter = adapter


        viewModel.favoritePostList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toMutableList())
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchPosts()
        adapter.notifyDataSetChanged()
    }

    private fun goToDetail(favoritePostId: Int) {
        findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToDetailPostActivity(favoritePostId))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}