package com.josycom.mayorjay.cardinfofinder.utils

import retrofit2.HttpException

class ApiError(private val throwable: Throwable?) : Throwable() {

    override var message: String = NETWORK_ERROR

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
                    else -> NETWORK_ERROR
                }
            }
            else -> {
                NETWORK_ERROR
            }
        }
    }

    companion object {
        const val NETWORK_ERROR = "Please Check Your Network Connection"
    }
}