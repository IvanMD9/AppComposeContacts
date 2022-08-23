package com.example.appcomposecontacts.presentation.favourites

import com.example.appcomposecontacts.data.model.FavouritesContact

data class FavouritesListState(
    val listFavourites : List<FavouritesContact> = emptyList()
)
