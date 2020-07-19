package com.josycom.mayorjay.cardinfofinder.network

import com.josycom.mayorjay.cardinfofinder.network.wrapper.CardInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{iin}")
    suspend fun getCardInfo(@Path("iin") iin: String): CardInfoResponse
}