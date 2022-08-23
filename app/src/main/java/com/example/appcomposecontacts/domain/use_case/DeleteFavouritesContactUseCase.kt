package com.example.appcomposecontacts.domain.use_case

import com.example.appcomposecontacts.data.model.FavouritesContact
import com.example.appcomposecontacts.domain.repository.RepositoryContact

class DeleteFavouritesContactUseCase(
    private val repositoryContact: RepositoryContact
) {

    suspend operator fun invoke(favouritesContact: FavouritesContact) {
        repositoryContact.deleteFavouritesContact(favouritesContact)
    }
}