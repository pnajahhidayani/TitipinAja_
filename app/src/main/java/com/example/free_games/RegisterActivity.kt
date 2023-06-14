package com.example.free_games

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_games.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_post.*


class RegisterActivity : AppCompatActivity()
{
    lateinit var binding: ActivitySignUpBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var username = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating Account")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.buttonSignUp.setOnClickListener{
            validateData()
        }
    }

    private fun validateData()
    {
        email = binding.registerEmail.text.toString().trim()
        username = binding.registerUsername.text.toString().trim()
        password = binding.registerPassword.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.registerEmail.error = "invalid email"
        }
        else if (TextUtils.isEmpty(password)) {
            binding.registerUsername.error = "Please enter password"
        }

        else if (username.length < 6) {
            binding.registerUsername.error = "username must be at least 6 characters long"
        }

        else if(password.length < 6) {
            binding.registerPassword.error = "Password must be at least 6 characters long"
        }
        else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp()
    {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val UserData: MutableMap<String, Any> = HashMap()
                UserData["username"] = username

                val DB = FirebaseFirestore.getInstance()
                DB.collection("Users")
                    .document(firebaseAuth.currentUser!!.uid)
                    .set(UserData)
                    .addOnSuccessListener {

                        progressDialog.dismiss()

                        val snackbar = Snackbar
                            .make(coordinator, "Registration sucesfull", Snackbar.LENGTH_LONG)
                        snackbar.show()

                        val handler = Handler()
                        handler.postDelayed({
                            finish()
                        }, 1500)
                    }

                    .addOnFailureListener{e ->
                        Toast.makeText(this, "Registration failed: $e", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }
            }

            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(this, "Registration failed: $e", Toast.LENGTH_SHORT).show()
            }
    }
}