package com.example.appcomposecontacts.presentation.util

sealed class NavigationScreen(val route : String) {
    object ContactsListScreen : NavigationScreen("list_contact")
    object ContactsDetailScreen : NavigationScreen("detail_contact")
}
