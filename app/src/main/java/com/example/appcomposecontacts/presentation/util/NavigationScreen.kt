package com.example.appcomposecontacts.presentation.util

sealed class NavigationScreen(val route : String) {
    object ContactsListScreen : NavigationScreen("list_contact")
    object AddEditContactsScreen : NavigationScreen("add_edit_contact")
    object DetailItemContactScreen : NavigationScreen("detail_item_contact")
    object ContactsFavouritesScreen : NavigationScreen("favourites_contact")
    object ContactsNumberPhoneScreen : NavigationScreen("number_phone_contact")

    fun withArgs(vararg args : String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
