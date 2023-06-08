package com.dimasnoufal.latihankotlinandroid.repository

import com.dimasnoufal.latihankotlinandroid.model.LoginResponse
import com.dimasnoufal.latihankotlinandroid.model.RegisterResponse
import com.dimasnoufal.latihankotlinandroid.model.ResponseUser
import com.dimasnoufal.latihankotlinandroid.model.UserByIdResponse
import retrofit2.Call

interface UserRepository {
    fun requestRegister(
        idUser: String?,
        username: String?,
        password: String?,
        namaLengkap: String?
    ): Call<RegisterResponse>

    fun requestLogin(
        username: String?,
        password: String?,
    ): Call<LoginResponse>

    fun getDataUser(): Call<ResponseUser>

    fun getDataUserById(idUser: String?): Call<UserByIdResponse>
}