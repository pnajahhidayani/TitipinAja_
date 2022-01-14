package com.example.free_games.models

import java.io.Serializable

data class ForumModel(var comment_number: Int? = null, var date: String? = null, var title: String? = null,
                      var username: String? = null, var view_number: Int? = null) : Serializable