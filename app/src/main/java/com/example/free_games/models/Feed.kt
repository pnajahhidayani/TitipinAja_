package com.example.free_games.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class Feed  @JvmOverloads constructor (

    @field:ElementList(name = "item", inline = true, required = false)
    @param:ElementList(name = "item", inline = true, required = false)

    @field:Path("channel")
    @param:Path("channel")
    var articleList: List<Article>? = null
)