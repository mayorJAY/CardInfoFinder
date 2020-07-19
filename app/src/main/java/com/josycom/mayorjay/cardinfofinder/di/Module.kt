package com.josycom.mayorjay.cardinfofinder.di

import com.josycom.mayorjay.cardinfofinder.network.Api
import com.josycom.mayorjay.cardinfofinder.network.source.DataSource
import com.josycom.mayorjay.cardinfofinder.network.source.DataSourceFactory
import com.josycom.mayorjay.cardinfofinder.repository.CardRepository
import com.josycom.mayorjay.cardinfofinder.repository.CardRepositoryImpl
import com.josycom.mayorjay.cardinfofinder.ui.CardInfoViewModel
import com.josycom.mayorjay.cardinfofinder.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single<Api> {
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }

    single {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        httpClient
    }

    single { DataSource(get()) }
    single { DataSourceFactory(get()) }
    single<CardRepository> { CardRepositoryImpl(get()) }

    factory { CardInfoViewModel(get()) }
}