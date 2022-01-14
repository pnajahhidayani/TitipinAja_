package com.example.free_games.HomeRecyclerView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.free_games.API.GMRetrofitInstance
import com.example.free_games.models.GameModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesViewModel: ViewModel() {
    var data: MutableLiveData<List<GameModel>>
    var postlist1: MutableList<GameModel>
    var postlist2: MutableList<GameModel>

    init {
        data = MutableLiveData()
        postlist1 = mutableListOf()
        postlist2 = mutableListOf()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<List<GameModel>>
    {
        makeApiCall()
        Log.e("Log", "home fetching data")
        return data
    }

    fun sortData(sort: Boolean)
    {
        if (sort == true) { data.postValue(postlist2) }
        else { data.postValue(postlist1) }
    }

    fun makeApiCall()
    {
        var API = GMRetrofitInstance.API
        var call = API.posts
        call.enqueue(object: Callback<List<GameModel>> {
            override fun onResponse(call: Call<List<GameModel>>, response: Response<List<GameModel>>)
            {
                postlist1 = (response.body() as List<GameModel>).toMutableList()
                postlist2 = (response.body() as List<GameModel>).toMutableList()
                postlist1.shuffle()
                data.postValue(postlist1)
                postlist2.sortByDescending { it.release_date }
//                randomizedListData.postValue(postlist2)
            }
            override fun onFailure(call: Call<List<GameModel>>, t: Throwable)
            {
                data.postValue(null)
            }
        })
    }
}




