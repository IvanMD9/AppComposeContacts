package com.example.appcomposecontacts.presentation.favourites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcomposecontacts.data.model.FavouritesContact
import com.example.appcomposecontacts.domain.use_case.UseCaseContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesListViewModel @Inject constructor(
    val useCaseContact: UseCaseContact
) : ViewModel () {

    var stateFavourites by mutableStateOf(FavouritesListState())
    private var getListFavourites : Job? = null

    init {
        getListFavourites()
    }

    fun onFavouritesEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.DeleteFavouritesContact -> {
                viewModelScope.launch {
                    useCaseContact.deleteFavouritesContactUseCase(event.favouritesContact)
                }
            }
        }
    }

    private fun getListFavourites() {
        getListFavourites?.cancel()
        getListFavourites = useCaseContact.getListFavouritesContactsUseCase().onEach { favouritesList ->
            stateFavourites = stateFavourites.copy(
                listFavourites = favouritesList as MutableList<FavouritesContact>
            )
        }.launchIn(viewModelScope)
    }
}