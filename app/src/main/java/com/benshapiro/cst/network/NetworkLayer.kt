package com.benshapiro.cst.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {

    private val BASE_URL = "https://android-interview.s3.eu-west-2.amazonaws.com/"

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val creditScoreService: CreditScoreService by lazy {
        retrofit.create(CreditScoreService::class.java)
    }

    val apiClient = ApiClient(creditScoreService)

}