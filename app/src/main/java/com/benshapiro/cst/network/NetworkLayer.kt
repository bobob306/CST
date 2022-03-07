package com.benshapiro.cst.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkLayer {

    // the location of the json file directory
    private val BASE_URL = "https://android-interview.s3.eu-west-2.amazonaws.com/"

    // handles serialization and deserialization
    @Singleton
    @Provides
    fun provideMoishi() : Moshi {
        val moshi = Moshi.Builder()
        return moshi.addLast(KotlinJsonAdapterFactory()).build()
    }

    // not necessary but allows us to see the output in the logcat which could be helpful with errors
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

    }

    // retrofit handles network calls, needs the internet permission
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient) : Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
    // selects service we define with proper annotations
    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) : CreditScoreService {
        return retrofit.create(CreditScoreService::class.java)
    }

    // class used to interface with creditscoreservice
    @Singleton
    @Provides
    fun provideApiClient(creditScoreService: CreditScoreService) : ApiClient {
        return ApiClient(creditScoreService)
    }

}