package com.example.free_games.HomeRecyclerView

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.free_games.GameDetails
import com.example.free_games.R
import com.example.free_games.models.GameModel
import java.util.*
import kotlin.collections.ArrayList

class GamesAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var items = mutableListOf<GameModel>()
    var initialitems = mutableListOf<GameModel>()
    private lateinit var context: Context
    private var Search: Boolean? = null

    companion object{
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        if (viewType == VIEW_TYPE_ONE)
        {
            return ItemsViewHolder1(
                LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
        }
        else
        {
            return ItemsViewHolder2(
                LayoutInflater.from(parent.context).inflate(R.layout.home_recyclerview_row2, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        if(getItemViewType(position) == VIEW_TYPE_ONE)
        {
            (holder as ItemsViewHolder1).bind(items.get(position))
        }
        else
        {
            (holder as ItemsViewHolder2).bind(items.get(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (Search == true)
        {
            return VIEW_TYPE_TWO
        }
        else
        {
            return VIEW_TYPE_ONE
        }
    }

    fun setView(mode: Boolean)
    {
        Search = mode
        //notifyDataSetChanged()
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

    fun submitList(list: List<GameModel>)
    {
        items.clear()
        initialitems.clear()

        items.addAll(list as MutableList<GameModel>)
        initialitems.apply { items?.let { addAll(it) } }

        notifyDataSetChanged()
    }

    fun Search(): Filter {
        return gameSearch
    }

    private val gameSearch = object: Filter()
    {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<GameModel> = ArrayList()
            if(constraint == null || constraint.isEmpty())
            {
                initialitems.let { filteredList.addAll(it) }
            }
            else {
                val query = constraint.toString().trim().toLowerCase()
                initialitems.forEach{
                    if(it.title.toLowerCase(Locale.ROOT).contains(query)) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                items.clear()
                items.addAll(results.values as ArrayList<GameModel>)
                notifyDataSetChanged()
            }
        }

    }

    fun Filter(): Filter {
        return GenreFilter
    }

    private val GenreFilter = object: Filter()
    {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<GameModel> = ArrayList()
            Log.e("constraint", constraint.toString())

            if(constraint == ",All" || constraint == ",All,Newest" || constraint == null || constraint.isEmpty())
            {
                initialitems.let { filteredList.addAll(it) }
            }
            else
            {
                var genres = constraint?.subSequence(1, constraint.length)?.split(",")
                Log.e("genres list", genres.toString())
                initialitems.forEach{ game ->
                    genres!!.forEach {
                        if(game.genre == it)
                        {
                            filteredList.add(game)
                        }
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                items.clear()
                items.addAll(results.values as ArrayList<GameModel>)
                notifyDataSetChanged()
            }
        }
    }

    class ItemsViewHolder1 constructor(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        var postmodel: GameModel? = null
        //val gameType: TextView = itemView.findViewById(R.id.game_type)
        val shortDescription: TextView = itemView.findViewById(R.id.short_description)
        val imageView: ImageView = itemView.findViewById(R.id.thumbnail)

        fun bind(game: GameModel)
        {
            this.postmodel = game
            //gameType.setText(game.genre)
            shortDescription.setText(game.short_description)
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .load(game.thumbnail)
                .into(imageView)
        }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, GameDetails::class.java)
            intent.putExtra("GameDetail", postmodel)
            context.startActivity(intent)
        }
    }

    class ItemsViewHolder2 constructor(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        var postmodel: GameModel? = null
        val GameImage: ImageView = itemView.findViewById(R.id.R2Tumbnail)
        val GameTitle: TextView = itemView.findViewById(R.id.R2GameTitle)
        val GameGenre: TextView = itemView.findViewById(R.id.R2GameType)

        fun bind(game: GameModel)
        {
            this.postmodel = game
            GameTitle.setText(game.title)
            GameGenre.setText(game.genre)
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
            Glide.with(itemView.context)
                .load(game.thumbnail)
                .into(GameImage)
        }
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val intent = Intent(context, GameDetails::class.java)
            intent.putExtra("GameDetail", postmodel)
            context.startActivity(intent)
        }
    }
}