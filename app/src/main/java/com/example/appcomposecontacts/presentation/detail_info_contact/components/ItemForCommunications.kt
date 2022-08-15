package com.example.appcomposecontacts.presentation.detail_info_contact.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemCommunications(
    image: Int,
    textCommunications: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(60.dp)
            .width(90.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "Image communications",
                modifier = Modifier.padding(vertical = 3.dp, horizontal = 15.dp)
            )
            Text(
                text = textCommunications,
                color = color,
                fontSize = 12.sp
            )
        }
    }
}