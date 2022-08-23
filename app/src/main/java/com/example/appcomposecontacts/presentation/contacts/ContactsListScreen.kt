package com.example.appcomposecontacts.presentation.contacts

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcomposecontacts.R
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.presentation.contacts.components.ContactsItem
import com.example.appcomposecontacts.presentation.contacts.components.MyContactCard
import com.example.appcomposecontacts.presentation.contacts.components.SearchText
import com.example.appcomposecontacts.presentation.util.NavigationScreen

@ExperimentalMaterialApi
@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel: ContactsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val stateHint = viewModel.searchText.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 70.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Image(
                painter = painterResource(id = R.drawable.ic_add_contacts),
                contentDescription = "Add contact",
                modifier = Modifier.clickable {
                    navController.navigate(NavigationScreen.AddEditContactsScreen.route)
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Контакты",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Normal
        )
        Spacer(modifier = Modifier.height(10.dp))
        SearchText(
            text = state.textSearch,
            hint = stateHint.hintSearch,
            onValueChange = {
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
            items(state.listContacts, { contact: Contact -> contact.id!! }) { contacts ->
                val stateList = rememberDismissState(
                    // Здесь происходит удаление свайпом
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.onEvent(ContactsEvent.DeleteContacts(contacts))
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
                        ContactsItem(contact = contacts, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    NavigationScreen.DetailItemContactScreen.route + "?contactId=${contacts.id}"
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}