package com.example.free_games

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.free_games.databinding.ActivityGameDetailsBinding
import com.example.free_games.models.GameModel
import kotlinx.android.synthetic.main.activity_game_details.view.*

class GameDetails : AppCompatActivity()
{
    lateinit var binding: ActivityGameDetailsBinding
    private lateinit var Context: Context
    private lateinit var obj : GameModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Context = this@GameDetails
        obj = intent.extras?.get("GameDetail") as GameModel
        loadUI(obj)
    }

    private fun loadUI(obj: GameModel)
    {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
        Glide.with(Context)
            .load(obj.thumbnail)
            .into(binding.harry)

        binding.GameTitle.text = obj.title
        binding.GameShortDescription.text = obj.short_description
        binding.GameDeveloper.text = obj.developer
        binding.GamePublisher.text = obj.publisher
        binding.GameReleaseDate.text = obj.release_date
        binding.GameGenre.text = obj.genre
        binding.OpenBrowserButton.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(obj.game_url))
            startActivity(i)
        }


        binding.GamePlayButton.setOnClickListener{
            if (isAppInstalled("com.google.android.youtube"))
            {
                val intent = Intent(Intent.ACTION_SEARCH)
                intent.setPackage("com.google.android.youtube")
                intent.putExtra("query", obj.title + " Gameplay")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            else
            {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/results?search_query=${obj.title + " Gameplay"}")
                )
                startActivity(browserIntent)
            }
        }
    }

    private  fun shareData() {
        val  textToShare = "contact"

        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
        sendIntent.type = "text/plain"

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    protected fun isAppInstalled(packageName: String?): Boolean {
        val mIntent = packageManager.getLaunchIntentForPackage(packageName!!)
        return mIntent != null
    }
}