package com.example.appcomposecontacts.presentation.add_edit_contacts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcomposecontacts.R
import com.example.appcomposecontacts.presentation.add_edit_contacts.components.TextFieldContactDetail
import com.example.appcomposecontacts.ui.theme.BackgroundButtonIcon
import com.example.appcomposecontacts.ui.theme.BackgroundMyCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DataContactScreen(
    navController: NavController,
    viewModel: ContactAddEditViewModel = hiltViewModel()
) {
    val nameState = viewModel.nameContact.value
    val surnameState = viewModel.surnameContact.value
    val companyState = viewModel.companyContact.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ContactAddEditViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is ContactAddEditViewModel.UiEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Отменить",
                color = BackgroundButtonIcon,
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    navController.navigateUp()
                })
            Text(
                text = "Контакт",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Готово",
                color = if (nameState.textContact.isBlank()
                    && surnameState.textContact.isBlank()
                ) Color.LightGray else BackgroundButtonIcon,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    viewModel.onEvent(ContactsAddEditEvent.SaveContact)
                }
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(BackgroundMyCard)
                .align(Alignment.CenterHorizontally)
        ) {
            // Доработать функционал с отображением букв имени и фамилии
            if (nameState.textContact.isBlank()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_contacts),
                    contentDescription = "My card",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp)
                        .size(120.dp)
                )
            } else {
                Text(
                    text = nameState.textContact.take(1).uppercase(),
                    fontSize = 65.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        TextFieldContactDetail(
            text = nameState.textContact,
            hint = nameState.hintContact,
            onValueChange = {
                viewModel.onEvent(ContactsAddEditEvent.EnteredName(it))
            },
            onFocusChange = {
                viewModel.onEvent(ContactsAddEditEvent.ChangeNameFocus(it))
            },
            isHintVisible = nameState.isHintVisibility,
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextFieldContactDetail(
            text = surnameState.textContact,
            hint = surnameState.hintContact,
            onValueChange = {
                viewModel.onEvent(ContactsAddEditEvent.EnteredSurname(it))
            },
            onFocusChange = {
                viewModel.onEvent(ContactsAddEditEvent.ChangeSurnameFocus(it))
            },
            isHintVisible = surnameState.isHintVisibility,
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextFieldContactDetail(
            text = companyState.textContact,
            hint = companyState.hintContact,
            onValueChange = {
                viewModel.onEvent(ContactsAddEditEvent.EnteredCompany(it))
            },
            onFocusChange = {
                viewModel.onEvent(ContactsAddEditEvent.ChangeCompanyFocus(it))
            },
            isHintVisible = companyState.isHintVisibility
        )
        Divider()
    }
}