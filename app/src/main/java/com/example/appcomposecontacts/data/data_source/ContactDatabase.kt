package com.example.appcomposecontacts.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appcomposecontacts.data.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun getDao() : Dao

    companion object {
        const val DB_NAME = "contacts_db"
    }
}