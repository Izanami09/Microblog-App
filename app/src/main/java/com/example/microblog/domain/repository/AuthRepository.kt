package com.example.microblog.domain.repository

import com.example.microblog.models.Token

interface AuthRepository{

    suspend fun login(username: String, password: String) : Result<Token>
}