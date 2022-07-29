package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import kotlinx.coroutines.flow.Flow

class SearchContactsUseCase(
    private val repositoryContact: RepositoryContact
) {
    operator fun invoke(search : String) : Flow<List<Contact>> {
        return repositoryContact.searchContacts(search)
    }
}