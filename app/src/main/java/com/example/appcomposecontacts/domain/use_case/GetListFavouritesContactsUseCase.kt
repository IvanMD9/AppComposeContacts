package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.FavouritesContact
import com.example.appcomposecontacts.domain.repository.RepositoryContact
import kotlinx.coroutines.flow.Flow

class GetListFavouritesContactsUseCase(
    private val repositoryContact: RepositoryContact
) {
    operator fun invoke() : Flow<List<FavouritesContact>> {
        return repositoryContact.getListFavouritesContacts()
    }
}