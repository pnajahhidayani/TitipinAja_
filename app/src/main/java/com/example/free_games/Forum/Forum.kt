package com.example.free_games.Forum

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.free_games.HomeRecyclerView.TopSpacingItemDecoration
import com.example.free_games.LoginActivity
import com.example.free_games.databinding.ForumRecyclerviewBinding
import com.example.free_games.models.ForumModel
import com.google.firebase.auth.FirebaseAuth


class Forum : Fragment()
{
    private lateinit var Posts: ArrayList<ForumModel>
    private lateinit var forumAdapter: ForumAdapter
    private var _binding: ForumRecyclerviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ForumViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        super.onCreate(savedInstanceState)
        _binding = ForumRecyclerviewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        Posts = arrayListOf()

        firebaseAuth = FirebaseAuth.getInstance()

        viewModel = ViewModelProviders.of(this).get(ForumViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(viewLifecycleOwner, {
            if (it != null)
            {
                forumAdapter.setData(it)
                forumAdapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_LONG).show()
            }
        })

        binding.AddPostButton.setOnClickListener {
            if(firebaseAuth.currentUser != null)
            {
                val intent = Intent(context, AddPostActivity::class.java)
                startActivity(intent)
            }
            else
            {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun initRecyclerView()
    {
        binding.forumRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            forumAdapter = ForumAdapter()
            this.adapter = forumAdapter
            val topSpacingItemDecoration = TopSpacingItemDecoration(5)
            addItemDecoration(topSpacingItemDecoration)
        }
    }
}

