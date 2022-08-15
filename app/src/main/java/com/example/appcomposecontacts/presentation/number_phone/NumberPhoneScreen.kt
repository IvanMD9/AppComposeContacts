package com.example.appcomposecontacts.presentation.number_phone

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appcomposecontacts.R
import com.example.appcomposecontacts.data.data_source.SessionManager
import com.example.appcomposecontacts.presentation.number_phone.components.ItemButtonPhone
import com.example.appcomposecontacts.presentation.number_phone.components.ItemChallengePhone
import com.example.appcomposecontacts.presentation.util.NavigationScreen
import com.example.appcomposecontacts.ui.theme.BackgroundButtonIcon
import com.example.appcomposecontacts.ui.theme.BackgroundChallengeButton
import com.example.appcomposecontacts.ui.theme.BackgroundPhoneButton

@Composable
fun NumberPhoneScreen(
    state: NumberPhoneState,
    spacingButton: Dp = 8.dp,
    modifier: Modifier = Modifier,
    onEvent: (NumberPhoneEvent) -> Unit,
    navController: NavController
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(start = 45.dp, end = 45.dp, bottom = 65.dp),
            verticalArrangement = Arrangement.spacedBy(spacingButton),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            val sM = SessionManager(context)
            val number = sM.savePhone(state.textPhone)
            Text(
                text = state.textPhone,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 1
            )
            Log.d("TAG", number.toString())
            Text(
                text = if (state.textPhone.isEmpty()) "" else "Добавить номер",
                color = BackgroundButtonIcon,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(NavigationScreen.AddEditContactsScreen.route)
                    }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemButtonPhone(
                    symbol = "1",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(1))
                    }
                )
                ItemButtonPhone(
                    symbol = "2",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(2))
                    }
                )
                ItemButtonPhone(
                    symbol = "3",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(3))
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemButtonPhone(
                    symbol = "4",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(4))
                    }
                )
                ItemButtonPhone(
                    symbol = "5",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(5))
                    }
                )
                ItemButtonPhone(
                    symbol = "6",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(6))
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemButtonPhone(
                    symbol = "7",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(7))
                    }
                )
                ItemButtonPhone(
                    symbol = "8",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(8))
                    }
                )
                ItemButtonPhone(
                    symbol = "9",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(9))
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ItemButtonPhone(
                    symbol = "*",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Symbol("*"))
                    }
                )
                ItemButtonPhone(
                    symbol = "0",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Number(0))
                    }
                )
                ItemButtonPhone(
                    symbol = "#",
                    modifier = Modifier
                        .background(BackgroundPhoneButton),
                    onClick = {
                        onEvent(NumberPhoneEvent.Symbol("#"))
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 110.dp)) {
                ItemChallengePhone(
                    modifier = Modifier
                        .background(BackgroundChallengeButton),
                    onClick = {},
                )
                Image(
                    painter = painterResource(id = R.drawable.delete_phone),
                    contentDescription = "Delete phone",
                    modifier = Modifier
                        .padding(start = 50.dp, top = 25.dp)
                        .clickable {
                            onEvent(NumberPhoneEvent.Delete)
                        }
                        .size(35.dp)
                )
            }
        }
    }
}