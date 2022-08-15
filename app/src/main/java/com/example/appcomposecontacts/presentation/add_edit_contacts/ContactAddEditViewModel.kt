package com.example.appcomposecontacts.presentation.add_edit_contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.use_case.UseCaseContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactAddEditViewModel @Inject constructor(
    private val useCaseContact: UseCaseContact,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _nameContact = mutableStateOf(ContactTextFieldState(
        hintContact = "Имя"
    ))
    val nameContact : State<ContactTextFieldState> = _nameContact

    private val _surnameContact = mutableStateOf(ContactTextFieldState(
        hintContact = "Фамилия"
    ))
    val surnameContact : State<ContactTextFieldState> = _surnameContact

    private val _companyContact = mutableStateOf(ContactTextFieldState(
        hintContact = "Компания"
    ))
    val companyContact : State<ContactTextFieldState> = _companyContact

    private var currentIdContact : Int ?= null

    // Разобраться что это и как работает
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("contactIdEdit")?.let { contactId ->
            if (contactId != -1) {
                viewModelScope.launch {
                    useCaseContact.getContactItemUseCase(contactId)?.also { contact ->
                        currentIdContact = contact.id
                        _nameContact.value = nameContact.value.copy(
                            textContact = contact.name,
                            isHintVisibility = false
                        )
                        _surnameContact.value = surnameContact.value.copy(
                            textContact = contact.surname ?: "",
                            isHintVisibility = false
                        )
                        _companyContact.value = companyContact.value.copy(
                            textContact = contact.company ?: "",
                            isHintVisibility = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ContactsAddEditEvent) {
        when (event) {
            is ContactsAddEditEvent.EnteredName -> {
                _nameContact.value = nameContact.value.copy(
                    textContact = event.nameValue
                )
            }
            is ContactsAddEditEvent.ChangeNameFocus -> {
                _nameContact.value = nameContact.value.copy(
                    isHintVisibility = !event.focusNameState.isFocused &&
                            nameContact.value.textContact.isBlank()
                )
            }
            is ContactsAddEditEvent.EnteredSurname -> {
                _surnameContact.value = surnameContact.value.copy(
                    textContact = event.surnameValue
                )
            }
            is ContactsAddEditEvent.ChangeSurnameFocus -> {
                _surnameContact.value = surnameContact.value.copy(
                    isHintVisibility = !event.focusSurnameState.isFocused &&
                            surnameContact.value.textContact.isBlank()
                )
            }
            is ContactsAddEditEvent.EnteredCompany -> {
                _companyContact.value = companyContact.value.copy(
                    textContact = event.companyValue
                )
            }
            is ContactsAddEditEvent.ChangeCompanyFocus -> {
                _companyContact.value = companyContact.value.copy(
                    isHintVisibility = !event.focusCompanyState.isFocused &&
                            companyContact.value.textContact.isBlank()
                )
            }
            is ContactsAddEditEvent.SaveContact -> {
                viewModelScope.launch {
                    try {
                        useCaseContact.addContactUseCase(
                            Contact(
                                id = currentIdContact,
                                name = nameContact.value.textContact,
                                surname = surnameContact.value.textContact,
                                company = companyContact.value.textContact
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save contact"
                            )
                        )
                    }
                }
            }
        }
    }
    sealed class UiEvent {
        data class ShowSnackBar(val message: String): UiEvent()
        object SaveNote: UiEvent()
    }
}