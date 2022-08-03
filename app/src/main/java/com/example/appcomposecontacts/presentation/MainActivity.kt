package com.example.appcomposecontacts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.appcomposecontacts.R
import com.example.appcomposecontacts.presentation.util.ButtonNavItem
import com.example.appcomposecontacts.presentation.util.NavigationButtonView
import com.example.appcomposecontacts.presentation.util.NavigationMenu
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
                    Scaffold(
                        bottomBar = {
                            NavigationMenu(
                                items = listOf(
                                    ButtonNavItem(
                                        "Избранные",
                                        "favourites_contact",
                                        R.drawable.ic_favourites
                                    ),
                                    ButtonNavItem(
                                        "Контакты",
                                        "list_contact",
                                        R.drawable.ic_contact
                                    ),
                                    ButtonNavItem(
                                        "Клавиши",
                                        "number_phone_contact",
                                        R.drawable.ic_number
                                    )
                                ),
                                navController = navController,
                                onClickItem = {
                                    navController.navigate(it.route)
                                }
                            )
                            Divider(modifier = Modifier.height(1.dp).background(Color.LightGray))
                        }
                    ) {
                        NavigationButtonView(navController = navController)
                    }
                }
            }
        }
    }
}