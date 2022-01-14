package com.example.free_games

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.free_games.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity()
{
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: FragmentLoginBinding
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Loging in")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        loadUI()
        return
    }

    private fun loadUI()
    {
        binding.loginButton.setOnClickListener{
            validateData()
        }

        binding.registerButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateData()
    {
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            binding.email.error = "invalid email"

        }
        else if (TextUtils.isEmpty(password))
        {
            binding.password.error = "Please enter password"
        }
        else
        {
            firebaseLogin()
        }
    }

    private fun firebaseLogin()
    {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Login is sucessfull", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener{e ->
                progressDialog.dismiss()
                Log.d("Log", e.toString())
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
    }
}