package com.example.appcomposecontacts.presentation.favourites

import com.example.appcomposecontacts.data.model.FavouritesContact

sealed class FavouritesEvent {
    data class DeleteFavouritesContact(val favouritesContact: FavouritesContact) : FavouritesEvent()
}
