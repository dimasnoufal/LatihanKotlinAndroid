package com.dimasnoufal.latihankotlinandroid.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimasnoufal.latihankotlinandroid.R
import com.dimasnoufal.latihankotlinandroid.databinding.ActivityHomeBinding
import com.dimasnoufal.latihankotlinandroid.model.DataUserItem
import com.dimasnoufal.latihankotlinandroid.ui.Adapter.UserAdapter
import com.dimasnoufal.latihankotlinandroid.ui.viewmodel.UserViewModel

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private val viewModel: UserViewModel by viewModels()
    private val userAdapter by lazy {
        UserAdapter()
    }
    lateinit var progresDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProgresDialog()
        observeDataUser()
    }

    private fun observeDataUser() {
        viewModel.getDataUser().observe(this@HomeActivity) { userList ->
            userList.let {
                progresDialog.dismiss()
                userAdapter.addDataUser(it.dataUser as List<DataUserItem>)
                binding.rvList.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
                binding.rvList.adapter = userAdapter
            }
        }
    }

    private fun setupProgresDialog() {
        progresDialog = ProgressDialog(this@HomeActivity)
        progresDialog.setTitle("Memuat Data")
        progresDialog.setMessage("Sedang mengambil data user")
        progresDialog.show()
    }
}