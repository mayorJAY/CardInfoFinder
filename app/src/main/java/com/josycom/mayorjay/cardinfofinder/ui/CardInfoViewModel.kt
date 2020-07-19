package com.josycom.mayorjay.cardinfofinder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josycom.mayorjay.cardinfofinder.repository.CardRepository
import kotlinx.coroutines.launch

class CardInfoViewModel(private val repository: CardRepository) : ViewModel() {


    fun getCardInfo(iin: String){
        viewModelScope.launch {
            repository.getCardInfo(iin)
        }
    }
}