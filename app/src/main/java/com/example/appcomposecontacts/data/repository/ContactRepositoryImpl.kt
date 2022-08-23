package com.example.appcomposecontacts.data.repository

import com.example.appcomposecontacts.data.data_source.Dao
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.data.model.FavouritesContact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import com.example.appcomposecontacts.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    override fun searchContacts(
        query: String
    ): Flow<Resource<List<Contact>>> = flow {
        emit(Resource.Loading(true))
        val localListings = dao.searchContacts(query).map { it }
        emit(Resource.Success(
            data = localListings.map { it }
        ))

        val isDbEmpty = localListings.isEmpty() && query.isBlank()
        val shouldJustLoadFromCache = !isDbEmpty
        if (shouldJustLoadFromCache) {
            emit(Resource.Loading(false))
            return@flow
        }
    }

    override fun getListFavouritesContacts(): Flow<List<FavouritesContact>> {
        return dao.getListFavouritesContacts()
    }

    override suspend fun addFavouritesContact(favouritesContact: FavouritesContact) {
        dao.addFavouritesContact(favouritesContact)
    }

    override suspend fun deleteFavouritesContact(favouritesContact: FavouritesContact) {
        dao.deleteFavouritesContact(favouritesContact)
    }
}
