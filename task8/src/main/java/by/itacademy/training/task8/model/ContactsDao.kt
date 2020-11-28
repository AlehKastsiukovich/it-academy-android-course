package by.itacademy.training.task8.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import by.itacademy.training.task8.entity.Contact

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)
}
