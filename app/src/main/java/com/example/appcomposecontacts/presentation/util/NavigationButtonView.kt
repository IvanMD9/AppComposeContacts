package com.example.appcomposecontacts.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appcomposecontacts.presentation.add_edit_contacts.DataContactScreen
import com.example.appcomposecontacts.presentation.contacts.ContactsScreen
import com.example.appcomposecontacts.presentation.detail_info_contact.DetailInfoScreen
import com.example.appcomposecontacts.presentation.favourites.FavouritesAddFromList
import com.example.appcomposecontacts.presentation.favourites.FavouritesScreen
import com.example.appcomposecontacts.presentation.number_phone.NumberPhoneScreen
import com.example.appcomposecontacts.presentation.number_phone.NumberPhoneViewModel

@ExperimentalMaterialApi
@Composable
fun NavigationButtonView(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.ContactsListScreen.route
    ) {
        composable(NavigationScreen.ContactsListScreen.route) {
            ContactsScreen(navController = navController)
        }
        composable(
            route = NavigationScreen.DetailItemContactScreen.route + "?contactId={contactId}",
            arguments = listOf(
                navArgument(name = "contactId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            DetailInfoScreen(navController = navController)
        }
        composable(
            route = NavigationScreen.AddEditContactsScreen.route + "?contactIdEdit={contactIdEdit}",
            arguments = listOf(
                navArgument(name = "contactIdEdit") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            DataContactScreen(navController = navController)
        }
        composable(route = NavigationScreen.ContactsFavouritesScreen.route) {
            FavouritesScreen(navController = navController)
        }
        composable(NavigationScreen.ContactsAddFromFavouritesScreen.route) {
            FavouritesAddFromList(navController = navController)
        }
        composable(NavigationScreen.ContactsNumberPhoneScreen.route) {
            val viewModel = viewModel<NumberPhoneViewModel>()
            val state = viewModel.state
            val spacing = 8.dp
            NumberPhoneScreen(
                state = state,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                onEvent = viewModel::onEvent,
                spacingButton = spacing,
                navController = navController
            )
        }
    }
}