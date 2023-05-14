package com.dimasnoufal.latihankotlinandroid.model

data class RegisterResponse(
    val sukses: Boolean? = null,
    val status: Int? = null,
    val pesan: String? = null,
    val data: DataRegister? = null
) {
    data class DataRegister(
        val id_user: String? = null,
        val username: String? = null,
        val password: String? = null,
        val nama_lengkap: String? = null
    )
}
