package com.example.cinemaapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cinemaapp.databinding.ActivitySignInScreenBinding

class SignInScreen : Activity() {

    private lateinit var binding: ActivitySignInScreenBinding
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        val signInButton: Button = findViewById(R.id.signInButton)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("emailKey", "")
        emailEditText.setText(savedEmail)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (!isEmailValid(emailEditText.text.toString())) {
                Toast.makeText(this, "Неверный ввод почты.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("emailKey", email)
                editor.apply()

                val intent = Intent(this, MainScreen::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Regex("[^@]+@[^@]+\\.[^@]+")
        return pattern.matches(email)
    }
}