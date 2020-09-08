package com.mistpaag.jsonplaceholder.post.views.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.adapters.PostAdapter
import com.mistpaag.jsonplaceholder.post.databinding.PostListFragmentBinding
import com.mistpaag.jsonplaceholder.post.models.PostResponse
import org.koin.android.ext.android.inject

class PostListFragment : Fragment() {

    companion object {
        fun newInstance() =
            PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private val viewModel by inject<PostListViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = PostAdapter{ post ->
            goToDetail(post)
        }

        binding.postsRecycler.layoutManager = GridLayoutManager(context, 1)

        binding.postsRecycler.adapter = adapter

        viewModel.fetchPosts()

        viewModel.postList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toMutableList())
        })

        return binding.root
    }

    private fun goToDetail(post: PostResponse) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}