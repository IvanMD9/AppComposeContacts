package com.example.appcomposecontacts.presentation.add_edit_contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcomposecontacts.presentation.add_edit_contacts.components.TextFieldContactDetail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DataContactScreen(
    navController: NavController,
    viewModel: ContactDetailViewModel = hiltViewModel()
) {
    val nameState = viewModel.nameContact.value
    val surnameState = viewModel.surnameContact.value
    val companyState = viewModel.companyContact.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ContactDetailViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is ContactDetailViewModel.UiEvent.SaveNote -> {
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
                color = Color.Blue,
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
                color = Color.Blue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    viewModel.onEvent(ContactsDetailEvent.SaveContact)
                })
        }
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .align(Alignment.CenterHorizontally)
        ) {
            // Реализовать логику подставления перой буквы имени
            Text(
                text = "M",
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        TextFieldContactDetail(
            text = nameState.textContact,
            hint = nameState.hintContact,
            onValueChange = {
                viewModel.onEvent(ContactsDetailEvent.EnteredName(it))
            },
            onFocusChange = {
                viewModel.onEvent(ContactsDetailEvent.ChangeNameFocus(it))
            },
            isHintVisible = nameState.isHintVisibility,
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextFieldContactDetail(
            text = surnameState.textContact,
            hint = surnameState.hintContact,
            onValueChange = {
                viewModel.onEvent(ContactsDetailEvent.EnteredSurname(it))
            },
            onFocusChange = {
                viewModel.onEvent(ContactsDetailEvent.ChangeSurnameFocus(it))
            },
            isHintVisible = surnameState.isHintVisibility,
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextFieldContactDetail(
            text = companyState.textContact,
            hint = companyState.hintContact,
            onValueChange = {
                viewModel.onEvent(ContactsDetailEvent.EnteredCompany(it))
            },
            onFocusChange = {
                viewModel.onEvent(ContactsDetailEvent.ChangeCompanyFocus(it))
            },
            isHintVisible = companyState.isHintVisibility
        )
        Divider()
    }
}