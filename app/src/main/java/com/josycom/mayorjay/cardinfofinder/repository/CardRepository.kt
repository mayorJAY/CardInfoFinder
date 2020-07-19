package com.josycom.mayorjay.cardinfofinder.repository

import com.josycom.mayorjay.cardinfofinder.network.wrapper.CardInfoResponse

interface CardRepository {
    suspend fun getCardInfo(iin: String): CardInfoResponse
}