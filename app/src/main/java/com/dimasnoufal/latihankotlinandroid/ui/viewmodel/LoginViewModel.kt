package com.dimasnoufal.latihankotlinandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimasnoufal.latihankotlinandroid.model.LoginResponse
import com.dimasnoufal.latihankotlinandroid.repository.UserRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

    val repository = UserRepositoryImpl()
    val loginObserveer: MutableLiveData<LoginResponse> = MutableLiveData()

    fun requestLogin(username: String, password: String): LiveData<LoginResponse> {
        repository.requestLogin(username, password).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginObserveer.value = response.body()
                } else {
                    loginObserveer.value = LoginResponse(sukses = false)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return loginObserveer
    }

}