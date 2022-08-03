package com.example.appcomposecontacts.presentation.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.appcomposecontacts.presentation.add_edit_contacts.DataContactScreen
import com.example.appcomposecontacts.presentation.contacts.ContactsScreen
import com.example.appcomposecontacts.presentation.favourites.FavouritesScreen
import com.example.appcomposecontacts.presentation.number_phone.NumberPhoneScreen

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
            route = NavigationScreen.ContactsDetailScreen.route + "?contactId={contactId}",
            arguments = listOf(
                navArgument(name = "contactId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            DataContactScreen(navController = navController)
        }
        composable(NavigationScreen.ContactsFavouritesScreen.route) {
            FavouritesScreen()
        }
        composable(NavigationScreen.ContactsNumberPhoneScreen.route) {
            NumberPhoneScreen()
        }
    }
}