package by.training.task7.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.training.task7.entity.Contact

@Database(entities = [Contact::class], version = 2, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {
        private const val ROOM_DATABASE_NAME = "contacts_database"
        private val INSTANCE: ContactsDatabase? = null

        fun getContactsDatabase(context: Context): ContactsDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                ContactsDatabase::class.java,
                ROOM_DATABASE_NAME,
            ).build()
        }
    }
}
