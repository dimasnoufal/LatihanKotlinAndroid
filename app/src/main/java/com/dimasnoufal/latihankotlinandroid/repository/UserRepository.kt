package com.dimasnoufal.latihankotlinandroid.repository

import com.dimasnoufal.latihankotlinandroid.model.RegisterResponse
import retrofit2.Call

interface UserRepository {
    fun requestRegister(
        idUser: String?,
        username: String?,
        password: String?,
        namaLengkap: String?
    ): Call<RegisterResponse>
}