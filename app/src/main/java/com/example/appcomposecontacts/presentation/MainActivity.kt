package com.example.appcomposecontacts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appcomposecontacts.presentation.add_edit_contacts.DataContactScreen
import com.example.appcomposecontacts.presentation.contacts.ContactsScreen
import com.example.appcomposecontacts.presentation.util.NavigationScreen
import com.example.appcomposecontacts.ui.theme.AppComposeContactsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppComposeContactsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
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
                    }
                }
            }
        }
    }
}