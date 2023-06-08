package com.dimasnoufal.latihankotlinandroid.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.dimasnoufal.latihankotlinandroid.databinding.ActivityUserDetailBinding
import com.dimasnoufal.latihankotlinandroid.ui.viewmodel.DetailUserByIdViewModel

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val ID_USER = "id_user"
    }

    lateinit var binding: ActivityUserDetailBinding
    private val detailUserViewModel: DetailUserByIdViewModel by viewModels()
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        callDetailuser()
        observeDetailUser()
    }

    private fun observeDetailUser() = with(detailUserViewModel) {
        userByIdObserver.observe(this@UserDetailActivity) { response ->
            if (response?.status == 1) {
                progressDialog.dismiss()
                response.dataUserById?.get(0).let {
                    binding.edtUsernameDetailUser.setText(it?.username.toString())
                    binding.edtNamaLengkapDetailUser.setText(it?.namaLengkap.toString())
                }
            } else {
                progressDialog.dismiss()
                Toast.makeText(this@UserDetailActivity, response.pesan, Toast.LENGTH_SHORT).show()
            }
        }    }

    private fun callDetailuser() = with(detailUserViewModel) {
        requestUserById(intent.getStringExtra(ID_USER).toString())
    }

    private fun setupListener() {
        progressDialog = ProgressDialog(this@UserDetailActivity)
        progressDialog.setTitle("Memuat Data")
        progressDialog.setTitle("Sedang memuat detai data user")
        progressDialog.show()
    }
}