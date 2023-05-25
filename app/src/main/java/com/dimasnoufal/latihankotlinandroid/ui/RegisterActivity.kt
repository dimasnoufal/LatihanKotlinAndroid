package com.dimasnoufal.latihankotlinandroid.ui

import android.app.ProgressDialog
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
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpProgresDialog()
        setupListener()
        observeRegister()

    }

    private fun setUpProgresDialog() {
        progressDialog = ProgressDialog(this@RegisterActivity)
        progressDialog.setTitle("Loading...")
        progressDialog.setMessage("Register sedanng di proses")
    }

    private fun observeRegister() {
        registerViewModel.registerObserver.observe(this@RegisterActivity) { response ->
            if (response?.sukses == true) {
                progressDialog.dismiss()
                Toast.makeText(this, response.pesan, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                progressDialog.dismiss()
                Toast.makeText(this@RegisterActivity, response.pesan, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListener() = with(binding) {
        val user_id =   UUID.randomUUID().toString()
        btnRegister.setOnClickListener {
            progressDialog.show()
            registerViewModel.requestRegister(
                user_id,
                etUsername.text.toString(),
                etNama.text.toString(),
                etPassword.text.toString()
            )
        }
    }
}