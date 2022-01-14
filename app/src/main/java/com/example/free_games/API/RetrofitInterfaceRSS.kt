package com.example.free_games.API

import com.example.free_games.models.Feed
import retrofit2.Call
import retrofit2.http.GET


interface RetrofitInterfaceRSS {
    @get:GET("/groups/freegamesfinders/rss/")
    val feed: Call<Feed?>?
}