package com.sdrss.mynotes.pojo


data class UserResponse(val status: String, val data: List<User>?, val message: String?)

data class User(
    val id: String?,
    val name: String?,
    val email: String?,
    val phone: String?,
    val age: String?,
    val password: String?
)