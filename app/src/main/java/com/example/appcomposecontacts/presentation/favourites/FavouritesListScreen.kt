package com.example.appcomposecontacts.presentation.favourites

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcomposecontacts.R
import com.example.appcomposecontacts.data.model.FavouritesContact
import com.example.appcomposecontacts.presentation.favourites.components.ItemFavouritesList
import com.example.appcomposecontacts.presentation.util.NavigationScreen
import com.example.appcomposecontacts.ui.theme.BackgroundButtonMenu

@ExperimentalMaterialApi
@Composable
fun FavouritesScreen(
    navController: NavController,
    viewModel: FavouritesListViewModel = hiltViewModel(),
) {

    val stateFavourites = viewModel.stateFavourites

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundButtonMenu)
                .padding(start = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_add_contacts),
                    contentDescription = "Add contact favourites",
                    modifier = Modifier.clickable {
                        navController.navigate(NavigationScreen.ContactsAddFromFavouritesScreen.route)
                    }
                )
                Text(
                    text = "Избранное",
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 110.dp)
                )
            }
        }
        Divider(Modifier.height(1.dp))
        if (stateFavourites.listFavourites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Нет избранных",
                    color = Color.Gray,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 15.dp)
                ) {
                    items(
                        stateFavourites.listFavourites,
                        { listFav: FavouritesContact -> listFav.id!! }
                    ) { contactsFav ->
                        val stateList = rememberDismissState(
                            // Здесь происходит удаление свайпом
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                    viewModel.onFavouritesEvent(
                                        FavouritesEvent.DeleteFavouritesContact(
                                            contactsFav
                                        )
                                    )
                                }
                                true
                            }
                        )
                        SwipeToDismiss(
                            state = stateList,
                            dismissThresholds = { FractionalThreshold(0.5f) },
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                val direction = stateList.dismissDirection ?: return@SwipeToDismiss
                                val color by animateColorAsState(
                                    when (stateList.targetValue) {
                                        DismissValue.Default -> Color.White
                                        DismissValue.DismissedToEnd -> Color.Red
                                        DismissValue.DismissedToStart -> Color.Transparent
                                    }
                                )
                                val alignment = when (direction) {
                                    DismissDirection.StartToEnd -> Alignment.CenterStart
                                    DismissDirection.EndToStart -> Alignment.CenterEnd
                                }
                                val icon = when (direction) {
                                    DismissDirection.EndToStart -> Icons.Default.Delete
                                    DismissDirection.StartToEnd -> null
                                }
                                val scale by animateFloatAsState(
                                    if (stateList.targetValue == DismissValue.Default) 0.75f else 1f
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(10.dp),
                                    contentAlignment = alignment
                                ) {
                                    icon?.let {
                                        Icon(
                                            imageVector = it,
                                            contentDescription = "Delete",
                                            tint = Color.Gray,
                                            modifier = Modifier
                                                .align(Alignment.CenterEnd)
                                                .scale(scale)
                                        )
                                    }
                                }
                            },
                            // Что именно будем удалять свайпом
                            dismissContent = {
                                ItemFavouritesList(
                                    favouritesContact = contactsFav,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}