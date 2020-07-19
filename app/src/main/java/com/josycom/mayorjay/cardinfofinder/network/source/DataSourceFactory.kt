package com.josycom.mayorjay.cardinfofinder.network.source

class DataSourceFactory (private val dataSource: DataSource) {
    fun remote(): DataSource {
        return dataSource
    }
}