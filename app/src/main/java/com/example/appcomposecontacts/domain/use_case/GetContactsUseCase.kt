package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import kotlinx.coroutines.flow.Flow

class GetContactsUseCase(
    private val repositoryContact: RepositoryContact
) {
    operator fun invoke() : Flow<List<Contact>> {
        return repositoryContact.getListContacts()
    }
}