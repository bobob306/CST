package com.benshapiro.cst.network

import com.benshapiro.cst.network.response.GetCreditScoreResponse
import retrofit2.Response
import retrofit2.http.GET

interface CreditScoreService {

    @GET("/endpoint.json")
    suspend fun getCreditScore(
    ): Response<GetCreditScoreResponse>

}