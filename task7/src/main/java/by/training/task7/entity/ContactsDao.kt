package by.training.task7.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert
    fun insertContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}
