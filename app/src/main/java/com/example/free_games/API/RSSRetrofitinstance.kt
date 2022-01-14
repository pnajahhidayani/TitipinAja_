package com.example.free_games.API

import com.example.free_games.Utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RSSRetrofitinstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.RSS_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    val api: RetrofitInterfaceRSS by lazy {
        retrofit.create(RetrofitInterfaceRSS::class.java)
    }
}

