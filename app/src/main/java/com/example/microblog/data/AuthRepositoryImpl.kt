package com.example.microblog.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.microblog.domain.repository.AuthRepository
import com.example.microblog.models.Token
import com.example.microblog.network.ApiService
import okhttp3.Credentials
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val microblogApiService: ApiService,
    context: Context
) : AuthRepository {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("TOKEN_KEY", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN_KEY", null)
    }


    override suspend fun login(username: String, password: String): Result<Token> {
        val credentials = Credentials.basic(username, password)
        //val authHeader = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)

        return try {
            val response = microblogApiService.getToken(credentials)
            if (response.isSuccessful) {
                val token = response.body()
                if (token != null) {
                    Result.success(token)
                }else{
                    Result.failure(Exception("Empty Response Body"))
                }
            }else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }

    }

}