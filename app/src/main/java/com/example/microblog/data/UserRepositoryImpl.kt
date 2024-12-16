package com.example.microblog.data

import com.example.microblog.domain.repository.UserRepository
import com.example.microblog.models.User
import com.example.microblog.network.ApiService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val microblogApiService: ApiService
) : UserRepository {
    override suspend fun getUser(id : Int): User {
        var response = microblogApiService.getUser(id).body()
        val nonNullableResponse = response!!
        return nonNullableResponse
    }

    override suspend fun getUsers(): List<User> {
        return microblogApiService.getUsers()
    }



}