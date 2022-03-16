package com.benshapiro.cst.network

import com.benshapiro.cst.network.response.GetCreditScoreResponse
import retrofit2.Response
import retrofit2.http.GET

interface CreditScoreService {

    /* Simple get request, would prefer
    to be able to put a parameter to
    simulate checking a username and password
     */
    @GET("/endpoint.json")
    suspend fun getCreditScore(
        // Tells the app what "form" should the returned data take
    // use response (rather than call) to handle failures/errors better
    ): Response<GetCreditScoreResponse>

}