package com.dimasnoufal.latihankotlinandroid.network

import com.dimasnoufal.latihankotlinandroid.model.LoginResponse
import com.dimasnoufal.latihankotlinandroid.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun registerUser(
        @Field("id_user") idUser: String?,
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("nama_lengkap") namaLengkap: String?
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<LoginResponse>
}