package com.example.appcomposecontacts.presentation.add_edit_contacts

import androidx.compose.ui.focus.FocusState

sealed class ContactsDetailEvent {
    data class EnteredName(val nameValue : String) : ContactsDetailEvent()
    data class ChangeNameFocus(val focusNameState: FocusState) : ContactsDetailEvent()
    data class EnteredSurname(val surnameValue : String) : ContactsDetailEvent()
    data class ChangeSurnameFocus(val focusSurnameState: FocusState) : ContactsDetailEvent()
    data class EnteredCompany(val companyValue : String) : ContactsDetailEvent()
    data class ChangeCompanyFocus(val focusCompanyState: FocusState) : ContactsDetailEvent()
    object SaveContact : ContactsDetailEvent()
}
