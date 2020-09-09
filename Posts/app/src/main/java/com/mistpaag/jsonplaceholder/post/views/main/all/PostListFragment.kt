package com.mistpaag.jsonplaceholder.post.views.main.all

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
import com.mistpaag.jsonplaceholder.post.databinding.PostListFragmentBinding
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.utils.Const
import org.koin.android.ext.android.inject

class PostListFragment : Fragment() {

    companion object {
        fun newInstance() =
            PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private val viewModel by inject<PostListViewModel> ()

    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_list_fragment, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = PostAdapter{
            if (it.clickOnImage){
                viewModel.updateFavoritePostStatus(it.postId, it.isFavorite)
            }else{
                goToDetail(it.postId)
            }
        }

        binding.postsRecycler.layoutManager = GridLayoutManager(context, 1)

        binding.postsRecycler.adapter = adapter

        viewModel.fetchPosts()

        viewModel.postList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toMutableList())
        })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchLocalPosts()
        adapter.notifyDataSetChanged()
    }

    private fun goToDetail(postId: Int) {
        findNavController().navigate(PostListFragmentDirections.actionPostListFragmentToDetailPostActivity(postId))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}