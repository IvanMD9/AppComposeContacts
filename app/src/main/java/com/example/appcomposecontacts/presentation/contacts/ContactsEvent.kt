package com.example.appcomposecontacts.presentation.contacts

import androidx.compose.ui.focus.FocusState
import com.example.appcomposecontacts.data.model.Contact

// Класс, описывающий все возможные действия на экране добавления и изменения(подписываться во ViewModel)
sealed class ContactsEvent {
    data class SearchContacts(val search : String) : ContactsEvent()
    data class EnteredSearchText(val value : String) : ContactsEvent()
    data class ChangeSearchFocus(val focusState: FocusState) : ContactsEvent()
    data class DeleteContacts(val contact : Contact) : ContactsEvent()
}
