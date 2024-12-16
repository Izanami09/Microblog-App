package com.example.microblog.di

import com.example.microblog.data.UserRepositoryImpl
import com.example.microblog.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun microblogApiService() : ApiService {
        val baseURL = "http://192.168.1.149:9000/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .build()

        val retrofitService by lazy { retrofit.create(ApiService::class.java)}

        return retrofitService
    }

    @Provides
    @Singleton
    fun provideUserRepoImplementation(apiService: ApiService) : UserRepositoryImpl{
        return UserRepositoryImpl(apiService)
    }

}