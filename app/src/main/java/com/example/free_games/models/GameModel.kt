package com.example.free_games.models

import java.io.Serializable

data class GameModel(var id:Int, var title: String, var thumbnail: String,
                     var short_description: String, var game_url : String,
                     var genre : String, var platform: String, var publisher: String,
                     var developer: String, var release_date: String,
                     var freetogame_profile_url: String) : Serializable
