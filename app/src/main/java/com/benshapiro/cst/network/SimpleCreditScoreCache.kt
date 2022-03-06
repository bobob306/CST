package com.benshapiro.cst.network

import com.benshapiro.cst.domain.models.CreditScore

object SimpleCreditScoreCache {

    val creditScoreMap = mutableMapOf<String, CreditScore>()

}