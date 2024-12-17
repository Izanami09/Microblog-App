package com.example.microblog.domain.repository

import com.example.microblog.models.Token
import com.example.microblog.models.User

interface UserRepository {
    suspend fun getUser(id:Int): Result<User>
    suspend fun  getUsers(): Result<List<User>>


}