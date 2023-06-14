package com.example.free_games.RSSFeed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.example.free_games.API.RSSRetrofitinstance
import com.example.free_games.models.Article
import com.example.free_games.models.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RSSViewModel(): ViewModel() {
    var RecyclerviewData: MutableLiveData<List<Article>>

    init {
        RecyclerviewData = MutableLiveData()
    }

//    fun getRSSFeed(): MutableLiveData<List<Article>> {
//        fetchRSSFeed()
//        Log.e("Log", "forum fetching data")
//        return RecyclerviewData
//    }
}

//    private fun fetchRSSFeed()
//    {
//        var API = RSSRetrofitinstance.api
//        var call = API.feed
//        call?.enqueue(object: Callback<Feed?>
//        {
//            override fun onResponse(call: Call<Feed?>, response: Response<Feed?>)
//            {
//                Log.d("Log", "rss fetching data")
//                var post = response.body()?.articleList
//                RecyclerviewData.postValue(post)
//            }
//
//            override fun onFailure(call: Call<Feed?>, t: Throwable)
//            {
//                RecyclerviewData.postValue(null)
//            }
//        })
//    }
//}