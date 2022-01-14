package com.example.free_games.Forum

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.free_games.models.ForumModel
import com.google.firebase.firestore.*

class ForumViewModel: ViewModel() {
    var RecyclerviewData: MutableLiveData<List<ForumModel>>
    private var Posts: ArrayList<ForumModel> = arrayListOf()
    private lateinit var DB: FirebaseFirestore

    init {
        RecyclerviewData = MutableLiveData()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<List<ForumModel>>
    {
        EventChangeListener()
        Log.e("Log", "forum fetching data")
        return RecyclerviewData
    }

    private fun EventChangeListener()
    {
        DB = FirebaseFirestore.getInstance()
        DB.collection("ForumPosts")
            .addSnapshotListener(object: EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?)
                {
                    if(e != null)
                    {
                        Log.e("FireStore Error", e.message.toString())
                        return
                    }

                    for(dc: DocumentChange in value?.documentChanges!!)
                    {
                        if(dc.type == DocumentChange.Type.ADDED)
                        {
                            Posts.add(dc.document.toObject(ForumModel::class.java))
                            RecyclerviewData.postValue(Posts)
                        }
                    }
                }
            })
    }
}