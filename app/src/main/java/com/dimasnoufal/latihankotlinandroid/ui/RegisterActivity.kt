package com.dimasnoufal.latihankotlinandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.dimasnoufal.latihankotlinandroid.databinding.ActivityRegisterBinding
import com.dimasnoufal.latihankotlinandroid.ui.viewmodel.RegisterViewModel
import java.util.UUID

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()
        observeRegister()

    }

    private fun observeRegister() {
        registerViewModel.registerObserver.observe(this@RegisterActivity) { response ->
            if (response?.sukses == true) {
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
            }
            else Toast.makeText(this@RegisterActivity, response.pesan, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListener() = with(binding) {
        val user_id =   UUID.randomUUID().toString()
        btnRegister.setOnClickListener {
            registerViewModel.requestRegister(
                user_id,
                etUsername.text.toString(),
                etNama.text.toString(),
                etPassword.text.toString()
            )
        }
    }
}