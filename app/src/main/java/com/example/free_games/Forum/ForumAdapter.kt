package com.example.free_games.Forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.free_games.R
import com.example.free_games.models.ForumModel
import com.example.free_games.models.GameModel

class ForumAdapter(): RecyclerView.Adapter<ForumAdapter.ItemsViewHolder>() {

    private var Posts: List<ForumModel> = ArrayList()

    fun setData(data: List<ForumModel>)
    {
        Posts = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder
    {
        return ItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.forum_recyclerview_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int)
    {
        holder.bind(Posts.get(position))
    }

    override fun getItemCount(): Int
    {
        return Posts.size
    }

    class ItemsViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        var post: ForumModel? = null
        var Username: TextView = itemView.findViewById(R.id.forum_username)
        var Title: TextView = itemView.findViewById(R.id.forum_header)
        var Date: TextView = itemView.findViewById(R.id.forum_date)
        var CommentNumber: TextView = itemView.findViewById(R.id.forum_comment_number)
        var ViewNumber: TextView = itemView.findViewById(R.id.forum_view_number)

        fun bind(post: ForumModel)
        {
            this.post = post
            Username.setText(post.username)
            Title.setText(post.title)
            Date.setText(post.date)
            CommentNumber.setText(post.comment_number.toString())
            ViewNumber.setText(post.view_number.toString())
        }

        init
        {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?)
        {
            TODO("open posts")
        }
    }
}