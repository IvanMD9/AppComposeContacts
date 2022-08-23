package com.example.appcomposecontacts.presentation.favourites

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.presentation.contacts.ContactsEvent
import com.example.appcomposecontacts.presentation.contacts.ContactsListViewModel
import com.example.appcomposecontacts.presentation.contacts.components.ContactsItem
import com.example.appcomposecontacts.presentation.contacts.components.MyContactCard
import com.example.appcomposecontacts.presentation.contacts.components.SearchText
import com.example.appcomposecontacts.presentation.util.NavigationScreen
import com.example.appcomposecontacts.ui.theme.BackgroundButtonIcon
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterialApi
@Composable
fun FavouritesAddFromList(
    navController: NavController,
    viewModel: ContactsListViewModel = hiltViewModel(),
    viewModelFav: FavouritesAddFromListViewModel = hiltViewModel()
) {
    val stateList = viewModel.state
    val stateHint = viewModel.searchText.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModelFav.eventFlow.collectLatest { eventFav ->
            when (eventFav) {
                is FavouritesAddFromListViewModel.UiEventFavourites.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = eventFav.message
                    )
                }
                is FavouritesAddFromListViewModel.UiEventFavourites.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 70.dp)
    ) {
        Text(
            text = "Выберите, чтобы добавить в Избранное",
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                text = "Контакты",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Normal
            )
            Text(
                text = "Отменить",
                color = BackgroundButtonIcon,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                modifier = Modifier
                    .clickable {
                        navController.navigateUp()
                    }
                    .padding(start = 50.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        SearchText(
            text = stateHint.textSearch,
            hint = stateHint.hintSearch,
            onValueChange = {
                viewModel.onEvent(ContactsEvent.EnteredSearchText(it))
                viewModel.onEvent(ContactsEvent.SearchContacts(it))
            },
            onFocusChange = {
                viewModel.onEvent(ContactsEvent.ChangeSearchFocus(it))
            },
            isHintVisible = stateHint.isHintVisibility,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Divider()
        Spacer(modifier = Modifier.height(10.dp))
        MyContactCard()
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp)
        ) {
            items(stateList.value.listContacts, { contact: Contact -> contact.id!! }) { contacts ->
                ContactsItem(contact = contacts, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Log.d("TAG", contacts.name + contacts.surname)
                        viewModelFav.onAddFavourites(FavouritesAddFromListEvent.AddFavouritesList)
                    }
                )
            }
        }
    }
}