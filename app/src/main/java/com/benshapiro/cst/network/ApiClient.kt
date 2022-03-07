package com.benshapiro.cst.network

import com.benshapiro.cst.network.response.GetCreditScoreResponse
import retrofit2.Response
import javax.inject.Inject

// class used to interface with creditscoreservice
class ApiClient @Inject constructor(
    private val CreditScoreService: CreditScoreService
) {
    // coroutines so there is no ANR, could also in the long run add to database
    suspend fun getCreditScore(): SimpleResponse<GetCreditScoreResponse> {
        return safeApiCall { CreditScoreService.getCreditScore()}
    }

    // check responses in simple response class, particularly helpful for failure
    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}