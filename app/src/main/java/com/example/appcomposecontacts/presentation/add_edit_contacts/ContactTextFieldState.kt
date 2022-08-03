package com.example.appcomposecontacts.presentation.add_edit_contacts

data class ContactTextFieldState(
    val textContact: String = "",
    val hintContact: String = "",
    val isHintVisibility: Boolean = true
)
