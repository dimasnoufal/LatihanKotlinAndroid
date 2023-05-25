package com.dimasnoufal.latihankotlinandroid.repository

import com.dimasnoufal.latihankotlinandroid.model.LoginResponse
import com.dimasnoufal.latihankotlinandroid.model.RegisterResponse
import com.dimasnoufal.latihankotlinandroid.network.NetworkConfig
import retrofit2.Call

class UserRepositoryImpl: UserRepository {
    override fun requestRegister(
        idUser: String?,
        username: String?,
        password: String?,
        namaLengkap: String?
    ): Call<RegisterResponse> {
        return NetworkConfig.getApiService().registerUser(idUser, username, password, namaLengkap)
    }

    override fun requestLogin(username: String?, password: String?): Call<LoginResponse> {
        return NetworkConfig.getApiService().loginUser(username, password)
    }

}