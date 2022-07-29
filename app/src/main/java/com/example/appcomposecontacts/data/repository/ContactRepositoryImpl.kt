package com.example.appcomposecontacts.data.repository

import com.example.appcomposecontacts.data.data_source.Dao
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(
    private val dao: Dao
) : RepositoryContact {

    override fun getListContacts(): Flow<List<Contact>> {
        return dao.getListContacts()
    }

    override suspend fun getContactById(id: Int): Contact? {
        return dao.getContactById(id)
    }

    override suspend fun addContact(contact: Contact) {
        dao.addContact(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact)
    }

    override fun searchContacts(search: String): Flow<List<Contact>> {
        return dao.searchContacts(search)
    }
}