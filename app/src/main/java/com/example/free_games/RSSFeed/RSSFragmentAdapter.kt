package com.example.free_games.RSSFeed

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.free_games.R
import com.example.free_games.models.Article




class RSSFragmentAdapter(): RecyclerView.Adapter<RSSFragmentAdapter.ItemsViewHolder>() {

    private var Posts: List<Article> = ArrayList()

    fun setData(data: List<Article>?)
    {
        Posts = data!!
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder
    {
        return ItemsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_rss_row, parent, false)
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
        var post: Article? = null
        var DateTime: TextView = itemView.findViewById(R.id.rss_date)
        var Title: TextView = itemView.findViewById(R.id.rss_title)
        var Body: TextView = itemView.findViewById(R.id.rss_description)

        fun bind(post: Article)
        {
            // Log.d("bind", "${post.pubDate} ${post.title} ${post.description}" )
            this.post = post
            DateTime.setText(post.pubDate?.subSequence(0, post.pubDate!!.length!!.minus(5)))
            Title.setText(post.title)
            Body.text = Html.fromHtml(post.description)
        }

        init
        {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, RSSDeatails::class.java)
            intent.putExtra("RSSDetail", post)
            context.startActivity(intent)
        }
    }
}