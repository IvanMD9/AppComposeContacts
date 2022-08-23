package com.example.appcomposecontacts.presentation.detail_info_contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcomposecontacts.R
import com.example.appcomposecontacts.data.data_source.SessionManager
import com.example.appcomposecontacts.presentation.detail_info_contact.components.ItemCommunications
import com.example.appcomposecontacts.presentation.util.NavigationScreen
import com.example.appcomposecontacts.ui.theme.BackgroundButtonIcon
import com.example.appcomposecontacts.ui.theme.BackgroundDetailInfo
import com.example.appcomposecontacts.ui.theme.BackgroundMyCard
import com.example.appcomposecontacts.ui.theme.BackgroundPhoneButton

@Composable
fun DetailInfoScreen(
    navController: NavController,
    //phone : String,
    viewModel: DetailInfoViewModel = hiltViewModel()
) {
    val stateName = viewModel.nameContactDetail
    val stateSurname = viewModel.surnameContactDetail
    val stateId = viewModel.currentIdContactEdit
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDetailInfo)
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_list_contacts),
                contentDescription = "Back list contacts",
                modifier = Modifier.clickable {
                    navController.navigateUp()
                }
            )
            Text(
                text = "Контакты",
                color = BackgroundButtonIcon,
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier.clickable {
                    navController.navigateUp()
                }
            )
            Text(
                text = "Править",
                color = BackgroundButtonIcon,
                fontSize = 18.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .padding(start = 200.dp)
                    .clickable {
                        navController.navigate(
                            NavigationScreen.AddEditContactsScreen.route + "?contactIdEdit=${stateId}"
                        )
                    }
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(BackgroundMyCard)
                .align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = if (stateName.value.textContact.isBlank()) stateSurname.value.textContact.take(
                    1
                )
                else stateName.value.textContact.take(1),
                color = Color.White,
                fontSize = 45.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stateName.value.textContact + " " + stateSurname.value.textContact,
            color = Color.Black,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                ItemCommunications(
                    image = R.drawable.message_item,
                    textCommunications = "написать",
                    color = BackgroundButtonIcon
                )
                ItemCommunications(
                    image = R.drawable.phone_item,
                    textCommunications = "вызов",
                    color = BackgroundButtonIcon
                )
                ItemCommunications(
                    image = R.drawable.whats_app_item,
                    textCommunications = "WhatsApp",
                    color = BackgroundButtonIcon
                )
                ItemCommunications(
                    image = R.drawable.mail_item,
                    textCommunications = "почта",
                    color = BackgroundPhoneButton
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 70.dp)
        ) {
            item {
                val context = LocalContext.current
                val sM = SessionManager(context)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            text = "телефон",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Normal
                        )
                        //val getPhone = sM.getPhone()
                        Text(
                            text = "",
                            color = BackgroundButtonIcon,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 18.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            text = "заметки",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Normal
                        )
                        BasicTextField(
                            value = "", onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .height(45.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "Отправить сообщение",
                            color = BackgroundButtonIcon,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Divider(
                            modifier = Modifier
                                .height(1.dp)
                        )
                        Text(
                            text = "Поделиться контактом",
                            color = BackgroundButtonIcon,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
                        )
                        Divider(modifier = Modifier.height(1.dp))
                        Text(
                            text = "Добавить в избранное",
                            color = BackgroundButtonIcon,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                ) {
                    Text(
                        text = "Добавить в контакты на случай ЧП",
                        color = BackgroundButtonIcon,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                ) {
                    Text(
                        text = "Поделиться геопозицией",
                        color = BackgroundButtonIcon,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                ) {
                    Text(
                        text = "Заблокировать абонента",
                        color = Color.Red,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}