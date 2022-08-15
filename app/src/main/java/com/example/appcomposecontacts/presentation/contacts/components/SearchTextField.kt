package com.example.appcomposecontacts.presentation.contacts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchText(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
                .background(Color.LightGray, CircleShape)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )
        if (isHintVisible) {
            Text(
                text = hint,
                color = Color.DarkGray,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)
            )
        }
    }
}