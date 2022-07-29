package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact

class GetContactItemUseCase(
    private val repositoryContact: RepositoryContact
) {
    suspend operator fun invoke(id : Int) : Contact? {
        return repositoryContact.getContactById(id)
    }
}