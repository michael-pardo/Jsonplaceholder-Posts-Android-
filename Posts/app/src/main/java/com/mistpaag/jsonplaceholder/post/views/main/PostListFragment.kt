package com.mistpaag.jsonplaceholder.post.views.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.databinding.PostListFragmentBinding
import org.koin.android.ext.android.bind
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


        viewModel.fetchPosts()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}