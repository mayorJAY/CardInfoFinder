package com.josycom.mayorjay.cardinfofinder.utils

import retrofit2.HttpException

class ApiError(private val throwable: Throwable?) : Throwable() {

    override var message: String = AppConstants.ERROR_MESSAGE

    init {
        errorResponse()
    }

    private fun errorResponse() {
        message = when (throwable) {
            is HttpException -> {
                val response = throwable.response()
                when (response!!.code()) {
                    400 -> {
                        val json = response.errorBody()?.string()!!
                        json
                    }
                    else -> AppConstants.ERROR_MESSAGE
                }
            }
            else -> {
                AppConstants.ERROR_MESSAGE
            }
        }
    }
}