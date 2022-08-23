package com.example.appcomposecontacts.presentation.favourites.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcomposecontacts.R
import com.example.appcomposecontacts.data.model.FavouritesContact
import com.example.appcomposecontacts.ui.theme.BackgroundMyCard

@Composable
fun ItemFavouritesList(
    favouritesContact: FavouritesContact,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(BackgroundMyCard)
            ) {
                Text(
                    text = favouritesContact.allName.take(1),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 15.dp)
                )
            }
            Text(
                text = favouritesContact.allName,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.width(100.dp),
                maxLines = 2,
                fontWeight = FontWeight.Medium
            )
            Image(
                painter = painterResource(id = R.drawable.info_fav_item_list),
                contentDescription = "Info favourites contact",
                modifier = Modifier
                    .clickable {

                    }
                    .padding(start = 70.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Divider()
    }
}