package com.example.appcomposecontacts.presentation.detail_info_contact

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcomposecontacts.domain.use_case.UseCaseContact
import com.example.appcomposecontacts.presentation.add_edit_contacts.ContactTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val useCaseContact: UseCaseContact,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _allNameContact = mutableStateOf(DetailInfoState())
    val allNameContact : State<DetailInfoState> = _allNameContact

    private val _nameContactDetail = mutableStateOf(ContactTextFieldState())
    val nameContactDetail : State<ContactTextFieldState> = _nameContactDetail

    private val _surnameContactDetail = mutableStateOf(ContactTextFieldState())
    val surnameContactDetail : State<ContactTextFieldState> = _surnameContactDetail

    var currentIdContactEdit : Int ?= null

    init {
        savedStateHandle.get<Int>("contactId")?.let { detailContact ->
            viewModelScope.launch {
                if (detailContact != -1) {
                    useCaseContact.getContactItemUseCase(detailContact)?.also { contactInfo ->
                        currentIdContactEdit = contactInfo.id
                        _nameContactDetail.value = nameContactDetail.value.copy(
                            textContact = contactInfo.name
                        )
                        _surnameContactDetail.value = surnameContactDetail.value.copy(
                            textContact = contactInfo.surname.toString()
                        )
                    }
                }
            }
        }
    }
}