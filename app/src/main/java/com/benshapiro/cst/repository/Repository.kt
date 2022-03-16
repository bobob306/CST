package com.benshapiro.cst.repository

import android.util.Log
import com.benshapiro.cst.domain.mappers.CreditScoreMapper
import com.benshapiro.cst.domain.models.CreditScore
import com.benshapiro.cst.network.ApiClient
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun getCreditScore(): CreditScore? {

        val request = apiClient.getCreditScore()
        // Probably not necessary to have the or, however it then catches anything not successful
        if (request.failed || !request.isSuccessful) {
            Log.d("return req from network", "no")
            return null
        }

        val creditScore = CreditScoreMapper.buildFrom(
            response = request.body
        )
        return creditScore
    }


}