package com.dimasnoufal.latihankotlinandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimasnoufal.latihankotlinandroid.model.RegisterResponse
import retrofit2.Callback
import com.dimasnoufal.latihankotlinandroid.repository.UserRepositoryImpl
import retrofit2.Call
import retrofit2.Response

class RegisterViewModel: ViewModel() {
    val repository = UserRepositoryImpl()
    val registerObserver: MutableLiveData<RegisterResponse> = MutableLiveData()

    fun requestRegister(
        idUser: String,
        username: String,
        password: String,
        namaLengkap: String
    ): LiveData<RegisterResponse> {
        repository.requestRegister(idUser, username, password, namaLengkap).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) registerObserver.value = response.body()
                else registerObserver.value = RegisterResponse(sukses = false)
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return registerObserver
    }
}