package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import com.example.appcomposecontacts.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchContactsUseCase(
    private val repositoryContact: RepositoryContact
) {
    operator fun invoke(search: String): Flow<Resource<List<Contact>>> {
        if (search.isBlank()) {
            return flow { }
        }
        return repositoryContact.searchContacts(search)
    }
}