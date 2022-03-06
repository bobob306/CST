package com.benshapiro.cst.network

import com.benshapiro.cst.network.response.GetCreditScoreResponse
import retrofit2.Response

class ApiClient(
    private val CreditScoreService: CreditScoreService
) {
    suspend fun getCreditScore(): SimpleResponse<GetCreditScoreResponse> {
        return safeApiCall { CreditScoreService.getCreditScore()}
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}