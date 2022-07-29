package com.example.appcomposecontacts.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey
    val id : Int? = null,
    val name : String,
    val surname : String?,
    val company : String?
)
