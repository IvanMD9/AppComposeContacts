package com.example.appcomposecontacts.presentation.add_edit_contacts

import androidx.compose.ui.focus.FocusState

sealed class ContactsAddEditEvent {
    data class EnteredName(val nameValue : String) : ContactsAddEditEvent()
    data class ChangeNameFocus(val focusNameState: FocusState) : ContactsAddEditEvent()
    data class EnteredSurname(val surnameValue : String) : ContactsAddEditEvent()
    data class ChangeSurnameFocus(val focusSurnameState: FocusState) : ContactsAddEditEvent()
    data class EnteredCompany(val companyValue : String) : ContactsAddEditEvent()
    data class ChangeCompanyFocus(val focusCompanyState: FocusState) : ContactsAddEditEvent()
    object SaveContact : ContactsAddEditEvent()
}
