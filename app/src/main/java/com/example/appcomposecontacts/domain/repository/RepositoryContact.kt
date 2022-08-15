package com.example.appcomposecontacts.domain.repository

import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.util.Resource
import kotlinx.coroutines.flow.Flow

interface RepositoryContact {

    fun getListContacts(): Flow<List<Contact>>
    suspend fun getContactById(id: Int): Contact?
    suspend fun addContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
    suspend fun searchContacts(search: String): List<Contact>
}