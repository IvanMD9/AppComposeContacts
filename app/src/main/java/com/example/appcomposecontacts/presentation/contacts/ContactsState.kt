package com.example.appcomposecontacts.presentation.contacts

import com.example.appcomposecontacts.data.model.Contact

data class ContactsState(
    val listContacts: MutableList<Contact> = mutableListOf(),
    val textSearch: String = "",
    val hintSearch: String = "",
    val isHintVisibility: Boolean = true
)
