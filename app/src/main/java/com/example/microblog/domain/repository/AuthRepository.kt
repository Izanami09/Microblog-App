package com.example.microblog.domain.repository

import com.example.microblog.models.NewUser
import com.example.microblog.models.Token
import com.example.microblog.models.User

interface AuthRepository{

    suspend fun login(username: String, password: String) : Result<Token>

    suspend fun registerUser(user: NewUser) : Result<User>
}