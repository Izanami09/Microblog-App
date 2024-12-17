package com.example.microblog.di

import android.app.Application
import android.content.Context
import com.example.microblog.data.AuthRepositoryImpl
import com.example.microblog.data.UserRepositoryImpl
import com.example.microblog.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun microblogApiService(okayHttpClient: OkHttpClient) : ApiService {
        val baseURL = "http://192.168.1.129:9000/"
        val retrofit = Retrofit.Builder()
            .client(okayHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .build()

        val retrofitService by lazy { retrofit.create(ApiService::class.java)}

        return retrofitService
    }

    @Provides
    @Singleton
    fun provideUserRepoImplementation(apiService: ApiService, authRepositoryImpl: AuthRepositoryImpl) : UserRepositoryImpl{
        return UserRepositoryImpl(apiService, authRepositoryImpl)
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideAuthRepoImplementation(apiService: ApiService, context: Context) : AuthRepositoryImpl{
        return AuthRepositoryImpl(apiService, context)
    }

}