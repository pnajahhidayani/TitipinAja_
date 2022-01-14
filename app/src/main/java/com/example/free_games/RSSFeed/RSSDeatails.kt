package com.example.free_games.RSSFeed

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.free_games.databinding.ActivityRssdeatailsBinding
import com.example.free_games.models.Article

class RSSDeatails : AppCompatActivity() {
    lateinit var binding: ActivityRssdeatailsBinding
    private lateinit var obj : Article

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRssdeatailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obj = intent.extras?.get("RSSDetail") as Article
        loadUI(obj)
    }

    private fun loadUI(obj: Article)
    {
        binding.RSSTitle.text = obj.title
        binding.RSSDate.text = obj.pubDate?.subSequence(0, obj.pubDate!!.length!!.minus(5))
        binding.RSSBody.text = Html.fromHtml(obj.description)
        binding.RSSBody.setMovementMethod(LinkMovementMethod.getInstance());
    }
}