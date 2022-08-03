package com.example.appcomposecontacts.presentation.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appcomposecontacts.ui.theme.BackgroundButtonIcon
import com.example.appcomposecontacts.ui.theme.BackgroundButtonMenu

@Composable
fun NavigationMenu(
    items: List<ButtonNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onClickItem: (ButtonNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = BackgroundButtonMenu,
        elevation = 5.dp
    ) {
        for (el in items) {
            val selected = el.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onClickItem(el) },
                selectedContentColor = BackgroundButtonIcon,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = el.image), contentDescription = el.name
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        if (selected) {
                            Text(
                                text = el.name, textAlign = TextAlign.Center, fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}