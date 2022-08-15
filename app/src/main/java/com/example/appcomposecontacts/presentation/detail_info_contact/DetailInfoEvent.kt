package com.example.appcomposecontacts.presentation.detail_info_contact

sealed class DetailInfoEvent {
    data class AllName(val allName : String) : DetailInfoEvent()
}
