package com.example.microblog.network

import com.example.microblog.models.User
import com.example.microblog.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/users")
    suspend fun getUsers() : UsersResponse

    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id:Int) : Response<User>
}