package com.example.appcomposecontacts.presentation.number_phone

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NumberPhoneViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(NumberPhoneState())
        private set

    fun onEvent(event: NumberPhoneEvent) {
        when (event) {
            is NumberPhoneEvent.Number -> enterNumber(event.number)
            is NumberPhoneEvent.Symbol -> enterSymbol(event.symbol)
            is NumberPhoneEvent.Delete -> deleteNumber()
        }
    }

    private fun deleteNumber() {
        when {
            state.textPhone.isNotBlank() -> state = state.copy(
                textPhone = state.textPhone.dropLast(1)
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.textPhone.length >= MAX_PHONE_LENGTH) {
            return
        } else {
            state = state.copy(
                textPhone = state.textPhone + number
            )
        }
    }

    private fun enterSymbol(symbol: String) {
        if (state.textPhone.length >= MAX_PHONE_LENGTH) {
            return
        } else {
            state = state.copy(
                textPhone = state.textPhone + symbol
            )
        }
    }

    companion object {
        private const val MAX_PHONE_LENGTH = 11
    }
}