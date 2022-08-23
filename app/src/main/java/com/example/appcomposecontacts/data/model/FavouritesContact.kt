package com.example.appcomposecontacts.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_contact")
data class FavouritesContact(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    val allName : String
)
