package com.dimasnoufal.latihankotlinandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dimasnoufal.latihankotlinandroid.model.ResponseUser
import com.dimasnoufal.latihankotlinandroid.repository.UserRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val userList: MutableLiveData<ResponseUser> = MutableLiveData()
    val repository = UserRepositoryImpl()

    fun getDataUser(): LiveData<ResponseUser> {
        repository.getDataUser().enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    userList.postValue(response.body())
                } else {
                    userList.postValue(emptyArray<ResponseUser>())
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                t.printStackTrace()
            }

        })
        return userList
    }

    private fun <T> MutableLiveData<T>.postValue(emptyArray: Array<T>): Array<T> {
        return emptyArray
    }

}