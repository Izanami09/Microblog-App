package com.example.microblog.domain.repository

import com.example.microblog.models.User

interface UserRepository {
    suspend fun getUser(id:Int): User
    suspend fun  getUsers(): List<User>
}