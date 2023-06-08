package com.dimasnoufal.latihankotlinandroid.model

import com.google.gson.annotations.SerializedName

 class DataUserItem(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nama_lengkap")
	val namaLengkap: String? = null,

	@field:SerializedName("id_user")
	val idUser: Any? = null,

	@field:SerializedName("username")
	val username: String? = null
)