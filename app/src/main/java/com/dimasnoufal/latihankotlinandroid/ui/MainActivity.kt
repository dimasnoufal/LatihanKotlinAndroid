package com.dimasnoufal.latihankotlinandroid.ui

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.dimasnoufal.latihankotlinandroid.databinding.ActivityMainBinding
import com.dimasnoufal.latihankotlinandroid.ui.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProgresDialog()
        setupListener()
        observerLogin()
    }

    private fun observerLogin() {
        loginViewModel.loginObserveer.observe(this@MainActivity) {response ->
            if (response?.sukses == true) {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity, response.pesan, Toast.LENGTH_SHORT).show()
                val register = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(register)
                finish()
            } else {
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity, response.pesan, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListener() = with(binding) {
        btnRegister.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            progressDialog.show()
            loginViewModel.requestLogin(username, password)
        }
    }

    private fun setupProgresDialog() {
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setTitle("Loading...")
        progressDialog.setMessage("Mohon menunggu proser login")
    }
}