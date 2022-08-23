package com.example.appcomposecontacts.presentation.favourites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcomposecontacts.data.model.FavouritesContact
import com.example.appcomposecontacts.domain.use_case.UseCaseContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesAddFromListViewModel @Inject constructor(
    private val useCaseContact: UseCaseContact
) : ViewModel() {

    private val _allName = mutableStateOf(FavouritesAddFromListState())
    val allName: State<FavouritesAddFromListState> = _allName

    private val _eventFlow = MutableSharedFlow<UiEventFavourites>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentIdContact: Int? = null

    fun onAddFavourites(event: FavouritesAddFromListEvent) {
        when (event) {
            is FavouritesAddFromListEvent.AddFavouritesList -> {
                viewModelScope.launch {
                    try {
                        useCaseContact.addFavouritesContactUseCase(
                            FavouritesContact(
                                id = currentIdContact,
                                allName = allName.value.name + " " + allName.value.surname
                            )
                        )
                        _eventFlow.emit(UiEventFavourites.SaveNote)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEventFavourites.ShowSnackBar(
                                message = e.message ?: "Error save favourites"
                            )
                        )
                    }
                }
            }
        }
    }
    sealed class UiEventFavourites {
        data class ShowSnackBar(val message: String) : UiEventFavourites()
        object SaveNote : UiEventFavourites()
    }
}

