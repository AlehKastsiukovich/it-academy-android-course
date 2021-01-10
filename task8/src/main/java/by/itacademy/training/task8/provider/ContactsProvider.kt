package by.itacademy.training.task8.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import by.itacademy.training.task8.model.db.ContactsDatabase
import by.itacademy.training.task8.model.entity.ROOM_CONTACTS_TABLE
import by.itacademy.training.task8.model.repository.ProviderRepository

class ContactsProvider : ContentProvider() {

    private val matcher = UriMatcher(UriMatcher.NO_MATCH)
    private var repository: ProviderRepository? = null

    init {
        matcher.addURI(AUTHORITY, ROOM_CONTACTS_TABLE, CONTACTS_DIR)
    }

    override fun onCreate(): Boolean {
        val database = context?.let { ContactsDatabase.getContactsDatabase(it) }
        val dao = database?.contactsDao()
        dao?.let { repository = ProviderRepository(it) }
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code = matcher.match(uri)
        return if (code == CONTACTS_DIR) {
            repository?.getContacts()
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? =
        if (matcher.match(uri) == CONTACTS_DIR) {
            URI_MENU
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = -1

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = -1

    companion object {
        const val AUTHORITY = "by.itacademy.training.task8.provider.ContactsProvider"
        const val URI_MENU = "content://$AUTHORITY/$ROOM_CONTACTS_TABLE"
        const val CONTACTS_DIR = 1
    }
}
