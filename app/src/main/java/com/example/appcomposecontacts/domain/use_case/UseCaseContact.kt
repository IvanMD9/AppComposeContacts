package com.example.appcomposecontacts.domain.use_case

data class UseCaseContact(
    val getContactsUseCase : GetContactsUseCase,
    val deleteContactUseCase: DeleteContactUseCase,
    val getContactItemUseCase: GetContactItemUseCase,
    val addContactUseCase: AddContactUseCase,
    val searchContactsUseCase: SearchContactsUseCase
)