package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import kotlinx.coroutines.flow.Flow

class SearchContactsUseCase(
    private val repositoryContact: RepositoryContact
) {
    suspend operator fun invoke(search : String) : List<Contact> {
        return repositoryContact.searchContacts(search)
    }
}