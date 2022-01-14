package com.example.free_games.Forum

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_games.databinding.ActivityAddPostBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_post.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class AddPostActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPostBinding
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPost.setOnClickListener{
           AddPostToFireStore()
        }
    }

    fun AddPostToFireStore()
    {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Posting Content")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()

        var username: String? = null
        val DB = FirebaseFirestore.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val docRef = DB.collection("Users").document(FirebaseAuth.getInstance().currentUser!!.uid)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    username = document.getString("username")
                    val ForumPosts: MutableMap<String, Any> = HashMap()
                    ForumPosts["comment_number"] = 0
                    ForumPosts["date"] = simpleDateFormat.format(Date())
                    ForumPosts["title"] = binding.titleEdittext.text.toString().trim()
                    ForumPosts["username"] = username!!
                    ForumPosts["view_number"] = 0
                    DB.collection("ForumPosts")
                        .add(ForumPosts)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            val snackbar = Snackbar
                                .make(coordinator, "Content Posted sucesfully", Snackbar.LENGTH_LONG)
                            snackbar.show()
                            val handler = Handler()
                            handler.postDelayed({
                                finish()
                            }, 1500)
                        }
                        .addOnFailureListener{e ->
                            Toast.makeText(this, "Error: $e", Toast.LENGTH_SHORT).show()
                            progressDialog.dismiss()
                            finish()
                        }
                }
                else
                {
                    Toast.makeText(this, "Problem occured", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error", exception)
            }
    }
}