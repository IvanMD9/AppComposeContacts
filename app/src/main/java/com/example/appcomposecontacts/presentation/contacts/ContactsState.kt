package com.example.appcomposecontacts.presentation.contacts

import com.example.appcomposecontacts.data.model.Contact

data class ContactsState(
    val listContacts: List<Contact> = emptyList(),
    val textSearch: String = "",
    val hintSearch: String = "",
    val isLoading: Boolean = false,
    val isHintVisibility: Boolean = true
)
