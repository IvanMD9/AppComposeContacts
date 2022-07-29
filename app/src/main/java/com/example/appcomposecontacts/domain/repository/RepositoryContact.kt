package com.example.appcomposecontacts.domain.repository

import com.example.appcomposecontacts.data.model.Contact
import kotlinx.coroutines.flow.Flow

interface RepositoryContact {

    fun getListContacts() : Flow<List<Contact>>
    suspend fun getContactById(id : Int) : Contact?
    suspend fun addContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
    fun searchContacts(search : String) : Flow<List<Contact>>
}