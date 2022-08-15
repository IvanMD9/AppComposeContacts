package com.example.appcomposecontacts.presentation.number_phone

sealed class NumberPhoneEvent {
    data class Number(val number : Int) : NumberPhoneEvent()
    data class Symbol(val symbol : String) : NumberPhoneEvent()
    object Delete : NumberPhoneEvent()
}
