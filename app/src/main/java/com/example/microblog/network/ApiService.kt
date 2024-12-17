package com.example.microblog.network

import com.example.microblog.models.Token
import com.example.microblog.models.User
import com.example.microblog.models.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ApiService {
    @GET("api/users")
    suspend fun getUsers() : Response<UsersResponse>

    @GET("api/users/{id}")
    suspend fun getUser(@Path("id") id:Int) : Response<User>


    @POST("api/tokens")
    suspend fun getToken(@Header("Authorization") authHeader: String): Response<Token>
}