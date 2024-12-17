package com.example.microblog.models


import kotlinx.serialization.Serializable

@Serializable
data class NewUser(
    val username:String,
    val email:String,
    val password: String

)
