package com.example.appcomposecontacts.data.repository

import com.example.appcomposecontacts.data.data_source.Dao
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import com.example.appcomposecontacts.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContactRepositoryImpl(
    private val dao: Dao
) : RepositoryContact {

//    override fun getListContacts(
//        fetchFromRemote: Boolean,
//        query: String
//    ): Flow<Resource<List<Contact>>> {
//        return flow {
//            emit(Resource.Loading(true))
//            val localListings = dao.searchContacts(query)
//            emit(Resource.Success(
//                data = localListings.map { it }
//            ))
//
//            val isDbEmpty = localListings.isEmpty() && query.isBlank()
//            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
//            if (shouldJustLoadFromCache) {
//                emit(Resource.Loading(false))
//                return@flow
//            }
//        }
//    }

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

    override suspend fun searchContacts(search: String): List<Contact> {
        return dao.searchContacts(search)
    }
}
