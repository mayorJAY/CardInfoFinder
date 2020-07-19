package com.josycom.mayorjay.cardinfofinder.repository

import com.josycom.mayorjay.cardinfofinder.network.source.DataSourceFactory
import com.josycom.mayorjay.cardinfofinder.network.wrapper.CardInfoResponse

class CardRepositoryImpl(private val dataSource: DataSourceFactory) : CardRepository {
    override suspend fun getCardInfo(iin: String): CardInfoResponse {
        return dataSource.remote().getCardInfo(iin)
    }
}