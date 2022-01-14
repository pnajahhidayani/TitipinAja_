package com.example.free_games


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.free_games.Forum.Forum
import com.example.free_games.HomeRecyclerView.Games
import com.example.free_games.RSSFeed.RSSFragment

class ViewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> { return Games() }
            1 -> { return RSSFragment() }
            2 -> { return Forum() }
            else -> { return Games() }
        }
    }
}