package com.example.appcomposecontacts.presentation.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import com.example.appcomposecontacts.domain.use_case.UseCaseContact
import com.example.appcomposecontacts.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsListViewModel @Inject constructor(
    private val repositoryContact: RepositoryContact,
    private val useCaseContact: UseCaseContact
) : ViewModel() {

    private val _state = mutableStateOf(ContactsState())
    val state: State<ContactsState> = _state

    private val _searchText = mutableStateOf(
        ContactsState(
            hintSearch = "Поиск"
        )
    )
    val searchText: State<ContactsState> = _searchText

    private var getContactsJob: Job? = null
    private var getSearchJob: Job? = null

    init {
        getContacts()
    }

    fun onEvent(event: ContactsEvent) {
        when (event) {
            is ContactsEvent.DeleteContacts -> {
                viewModelScope.launch {
                    useCaseContact.deleteContactUseCase(event.contact)
                }
            }
            is ContactsEvent.SearchContacts -> {
                _state.value = state.value.copy(
                    textSearch = event.search
                )
                getSearchJob?.cancel()
                getSearchJob = viewModelScope.launch {
                    delay(500L)
                    getSearchContacts()
                }
            }
            is ContactsEvent.EnteredSearchText -> {
                _searchText.value = searchText.value.copy(
                    textSearch = event.value
                )
            }
            is ContactsEvent.ChangeSearchFocus -> {
                _searchText.value = searchText.value.copy(
                    isHintVisibility = !event.focusState.isFocused
                            && searchText.value.textSearch.isBlank()
                )
            }
        }
    }

    private fun getContacts() {
        getContactsJob?.cancel()
        getContactsJob = useCaseContact.getContactsUseCase().onEach { contacts ->
            _state.value = state.value.copy(
                listContacts = contacts as MutableList<Contact>
            )
        }.launchIn(viewModelScope)
    }

    private fun getSearchContacts(
        query: String = _state.value.textSearch.lowercase(),
    ) {
        viewModelScope.launch {
            repositoryContact
                .searchContacts(query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { contacts ->
                                _state.value = state.value.copy(
                                    listContacts = contacts
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            _state.value = state.value.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}