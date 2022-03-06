package com.benshapiro.cst.repository

import android.util.Log
import com.benshapiro.cst.domain.mappers.CreditScoreMapper
import com.benshapiro.cst.domain.models.CreditScore
import com.benshapiro.cst.network.NetworkLayer
import com.benshapiro.cst.network.SimpleCreditScoreCache
import javax.inject.Inject

class Repository @Inject constructor(){

    suspend fun getCreditScore(): CreditScore? {

        val request = NetworkLayer.apiClient.getCreditScore()
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