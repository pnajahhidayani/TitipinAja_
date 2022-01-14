package com.example.free_games.RSSFeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.free_games.HomeRecyclerView.TopSpacingItemDecoration
import com.example.free_games.R
import com.example.free_games.databinding.FragmentRssBinding
import kotlinx.android.synthetic.main.fragment_rss.*


class RSSFragment : Fragment() {

    private lateinit var RSSAdapter: RSSFragmentAdapter
    lateinit var binding: FragmentRssBinding
    private lateinit var viewModel: RSSViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        return inflater.inflate(R.layout.fragment_rss, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel = ViewModelProviders.of(this).get(RSSViewModel::class.java)
        viewModel.getRSSFeed().observe(viewLifecycleOwner, {
            if (it != null)
            {
                RSSAdapter.setData(it)
                RSSAdapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView()
    {
        rssrecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            RSSAdapter = RSSFragmentAdapter()
            this.adapter = RSSAdapter
            val topSpacingItemDecoration = TopSpacingItemDecoration(3)
            addItemDecoration(topSpacingItemDecoration)
        }
    }
}
