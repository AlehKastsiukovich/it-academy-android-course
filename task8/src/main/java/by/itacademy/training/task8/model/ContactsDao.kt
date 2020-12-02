package by.itacademy.training.task8.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import by.itacademy.training.task8.entity.Contact
import io.reactivex.Completable
import io.reactivex.Observable

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

    @Query("SELECT * FROM contacts")
    fun getAllContactsRx(): Observable<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertContactRx(contact: Contact): Completable

    @Update
    fun updateContactRx(contact: Contact): Completable

    @Delete
    fun deleteContactRx(contact: Contact): Completable
}
