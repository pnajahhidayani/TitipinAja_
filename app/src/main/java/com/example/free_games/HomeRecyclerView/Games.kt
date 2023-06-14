package com.example.free_games.HomeRecyclerView

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.free_games.FilterActivity
import com.example.free_games.R
import kotlinx.android.synthetic.main.recycler_view_fragment.*


class Games : Fragment() {

    private lateinit var GamesAdapter: GamesAdapter
    private lateinit var viewModel: GamesViewModel
    private lateinit var searchBar: SearchView
    private lateinit var FilterButton: ImageButton
    private var checkboxList = ",All"
    private var filtered = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.recycler_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        searchBar = view.findViewById(R.id.searchView)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                GamesAdapter.setView(true)
                GamesAdapter?.Search()?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (filtered == true) {
                    //if ("")
                    checkboxList = ",All"
                    filtered = false
                }

                if(newText.isNullOrEmpty())
                {
                    GamesAdapter.setView(false)
                }

                else
                {
                    GamesAdapter.setView(true)
                }

                GamesAdapter?.Search()?.filter(newText)
                return true
            }
        })

        FilterButton = view.findViewById(R.id.FilterButton)
        FilterButton.setOnClickListener{
            val intent = Intent(context, FilterActivity::class.java)
            intent.putExtra("Checkboxes",checkboxList)
            startActivityForResult(intent, 1)
        }

        initRecyclerView()

        viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)
//        viewModel.getRecyclerListDataObserver().observe(viewLifecycleOwner, {
//            if (it != null)
//            {
//                recycler_View.apply {recycler_View.adapter = GamesAdapter}
//                GamesAdapter.submitList(it)
//                GamesAdapter.notifyDataSetChanged()
//
//            }
//            else
//            {
//                Toast.makeText(activity, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show()
//            }
//        })
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                val result = data!!.getStringExtra("result")
                checkboxList = result!!
                if("Newest" in result)
                {
                    viewModel.sortData(true)
                    //recycler_View.adapter = GamesAdapter
                    Log.e("activity return", "sortdata")
                    GamesAdapter.notifyDataSetChanged()

                }
                else
                {
                    viewModel.sortData(false)
                    Log.e("activity return", "shuffle data")
                    GamesAdapter.notifyDataSetChanged()
                }
                Log.e("checkboxHandler", result.toString())
                filtered = true
                GamesAdapter?.Filter()?.filter(result.toString())
            }
            if (resultCode == Activity.RESULT_CANCELED)
            {
                Log.d("LOG", "Filter not selected")
            }
        }
    }

    private fun initRecyclerView()
    {
        recycler_View.apply {
            layoutManager = LinearLayoutManager(activity)
            GamesAdapter = GamesAdapter()
            recycler_View.adapter = GamesAdapter
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
        }
    }
}