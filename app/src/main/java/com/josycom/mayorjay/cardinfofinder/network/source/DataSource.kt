package com.josycom.mayorjay.cardinfofinder.network.source

import com.josycom.mayorjay.cardinfofinder.network.Api
import com.josycom.mayorjay.cardinfofinder.network.wrapper.CardInfoResponse

class DataSource(private val api: Api) {
    suspend fun getCardInfo(iin: String): CardInfoResponse {
        return api.getCardInfo(iin)
    }
}