package com.example.microblog.data

import com.example.microblog.domain.repository.UserRepository
import com.example.microblog.models.Token
import com.example.microblog.models.User
import com.example.microblog.network.ApiService
import okhttp3.Credentials
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val microblogApiService: ApiService
) : UserRepository {

    override suspend fun getUser(id: Int): Result<User> {
        return try {
            val response = microblogApiService.getUser(id) // Call the network layer
            if (response.isSuccessful) {
                val user = response.body()
                if (user != null) {
                    // Convert to domain model and wrap in Result.Success
                    Result.success(user)
                } else {
                    // Handle case where the response body is null
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                // Handle unsuccessful response
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            // Handle exceptions (e.g., network failure)
            Result.failure(e)
        }
    }

    override suspend fun getUsers(): Result<List<User>> {
            return try {
                val response = microblogApiService.getUsers() // Call the network layer
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        // Convert to domain model and wrap in Result.Success
                        Result.success(userResponse.items)
                    } else {
                        // Handle case where the response body is null
                        Result.failure(Exception("Empty response body"))
                    }
                } else {
                    // Handle unsuccessful response
                    Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
                }
            } catch (e: Exception) {
                // Handle exceptions (e.g., network failure)
                Result.failure(e)
            }
        }


}


