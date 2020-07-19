package com.josycom.mayorjay.cardinfofinder.network.source

class DataSourceFactory constructor(private val dataSource: DataSource) {
    fun remote(): DataSource {
        return dataSource
    }
}