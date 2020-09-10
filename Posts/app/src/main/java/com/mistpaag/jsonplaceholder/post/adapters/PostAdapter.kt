package com.mistpaag.jsonplaceholder.post.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mistpaag.jsonplaceholder.post.R
import com.mistpaag.jsonplaceholder.post.models.AdapterClick
import com.mistpaag.jsonplaceholder.post.models.post.PostResponse
import com.mistpaag.jsonplaceholder.post.utils.Const
import com.mistpaag.jsonplaceholder.post.utils.inflate
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter(private val itemClick:(AdapterClick)-> Unit) : ListAdapter<PostResponse, PostAdapter.ViewHolder>(PostDiffCallback()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.post_item), itemClick = itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    inner class ViewHolder(itemView: View, var itemClick: (AdapterClick) -> Unit) : RecyclerView.ViewHolder(itemView){
        fun bindTo(post: PostResponse, position: Int){
            with(post){
                itemView.body_text.text = body
                itemView.tittle_text.text = title
                loadFavoriteState(favorite)
                loadTakenState(taken)
                itemView.setOnClickListener {
                    itemClick(AdapterClick(post.id, false,favorite))
                }
                itemView.favorite_icon.setOnClickListener {
                    favorite = !favorite
                    loadFavoriteState(favorite)
                    itemClick(AdapterClick(post.id, true, favorite))
                }
            }

        }

        private fun loadFavoriteState(favorite:Boolean){
            if (favorite) {
                itemView.favorite_icon.load(R.drawable.favorite)
            }else{
                itemView.favorite_icon.load(R.drawable.unfavorite)
            }
        }

        private fun loadTakenState(isTaken:Boolean){
            if (isTaken) {
                itemView.taken_image.load(R.drawable.taken)
            }else{
                itemView.taken_image.load(R.drawable.untaken)
            }
        }
    }

}



class PostDiffCallback : DiffUtil.ItemCallback<PostResponse>() {

    override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
        return oldItem == newItem
    }
}