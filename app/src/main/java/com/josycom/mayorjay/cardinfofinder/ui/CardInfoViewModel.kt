package com.josycom.mayorjay.cardinfofinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josycom.mayorjay.cardinfofinder.network.wrapper.CardInfoResponse
import com.josycom.mayorjay.cardinfofinder.repository.CardRepository
import com.josycom.mayorjay.cardinfofinder.utils.Result
import kotlinx.coroutines.launch

class CardInfoViewModel(private val repository: CardRepository) : ViewModel() {

    private val _checkCard = MutableLiveData<Result<Nothing>>()
    val checkCard: LiveData<Result<Nothing>> = _checkCard

    private val _cardInfo = MutableLiveData<CardInfoResponse>()
    val cardInfo: LiveData<CardInfoResponse> = _cardInfo

    fun getCardInfo(iin: String) {
        _checkCard.postValue(Result.Loading)
        viewModelScope.launch {
            try {
                val response = repository.getCardInfo(iin)
                _cardInfo.postValue(response)
                _checkCard.postValue(Result.Success())
            } catch (error: Throwable) {
                _checkCard.postValue(Result.Error(error))
            }
        }
    }
}