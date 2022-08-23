package com.example.appcomposecontacts.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcomposecontacts.data.model.Contact
import com.example.appcomposecontacts.data.model.FavouritesContact

@Database(entities = [Contact::class, FavouritesContact::class], version = 2)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {
        const val DB_NAME = "contacts_db"
    }
}