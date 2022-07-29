package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact

class AddContactUseCase(
    private val repositoryContact: RepositoryContact
) {

    suspend operator fun invoke(contact: Contact) {
        if (contact.name.isBlank()) {
            throw Exception("Введите имя")
        }
        repositoryContact.addContact(contact)
    }
}