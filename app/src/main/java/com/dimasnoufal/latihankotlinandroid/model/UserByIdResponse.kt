package com.dimasnoufal.latihankotlinandroid.model

import com.google.gson.annotations.SerializedName

 class UserByIdResponse(

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("dataUserById")
	val dataUserById: List<DataUserByIdItem?>? = null,

	@field:SerializedName("status")
	val status: Int? = null
)