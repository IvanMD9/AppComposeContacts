package com.example.appcomposecontacts.presentation.contacts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcomposecontacts.R

@Composable
fun MyContactCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(horizontalArrangement = Arrangement.Start) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_contacts),
                    contentDescription = "My card",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp)
                )
            }
            Text(
                text = "Моя карточка",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider(modifier = Modifier.height(20.dp))
}