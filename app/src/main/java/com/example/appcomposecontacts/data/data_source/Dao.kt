package com.example.appcomposecontacts.data.data_source

import androidx.room.*
import androidx.room.Dao
import com.example.appcomposecontacts.data.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM contacts")
    fun getListContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts WHERE id =:id")
    suspend fun getContactById(id: Int): Contact?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts WHERE name LIKE :search OR surname LIKE :search")
    fun searchContacts(search : String) : Flow<List<Contact>>
}