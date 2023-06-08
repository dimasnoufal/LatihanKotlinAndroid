package com.dimasnoufal.latihankotlinandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimasnoufal.latihankotlinandroid.model.UserByIdResponse
import com.dimasnoufal.latihankotlinandroid.repository.UserRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserByIdViewModel: ViewModel() {
    val repository = UserRepositoryImpl()
    val userByIdObserver: MutableLiveData<UserByIdResponse> = MutableLiveData()

    fun requestUserById(idUser: String): LiveData<UserByIdResponse> {
        repository.getDataUserById(idUser).enqueue(object : Callback<UserByIdResponse> {
            override fun onResponse(call: Call<UserByIdResponse>, response: Response<UserByIdResponse>) {
                if (response.isSuccessful) userByIdObserver.value = response.body()
                else userByIdObserver.value = UserByIdResponse(status = 0)
            }

            override fun onFailure(call: Call<UserByIdResponse>, t: Throwable) {
                t.printStackTrace()
                userByIdObserver.value = UserByIdResponse(pesan = t.message)
            }
        })
        return userByIdObserver
    }
}