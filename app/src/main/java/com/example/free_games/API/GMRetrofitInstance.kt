package com.example.free_games.API

import com.example.free_games.Utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GMRetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val API: RetrofitInterfaceGM by lazy {
        retrofit.create(RetrofitInterfaceGM::class.java)
    }
}
