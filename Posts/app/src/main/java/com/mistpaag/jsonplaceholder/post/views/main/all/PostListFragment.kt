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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.adapters.PostAdapter
import com.mistpaag.jsonplaceholder.post.databinding.PostListFragmentBinding
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.utils.Const
import com.mistpaag.jsonplaceholder.post.utils.SwipeToDeleteCallback
import org.koin.android.ext.android.inject

class PostListFragment : Fragment() {

    companion object {
        fun newInstance() =
            PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private val viewModel by inject<PostListViewModel> ()

    private lateinit var postsList : MutableList<PostResponse>

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

        binding.postsRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.postsRecycler.adapter = adapter

        swipeToDelete()

        viewModel.fetchPosts()

        viewModel.postList.observe(viewLifecycleOwner, Observer {
            postsList = it.toMutableList()
            adapter.submitList(postsList)
        })

        return binding.root
    }

    private fun swipeToDelete(){


        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                viewModel.deleteItem(postsList[pos].id)
                postsList.removeAt(pos)
                adapter.notifyItemRemoved(pos)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.postsRecycler)
    }

    override fun onPause() {
        super.onPause()
        adapter.notifyDataSetChanged()
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