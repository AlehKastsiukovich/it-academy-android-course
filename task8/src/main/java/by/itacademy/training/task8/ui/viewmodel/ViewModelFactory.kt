package by.itacademy.training.task8.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.itacademy.training.task8.R
import by.itacademy.training.task8.model.db.ContactsDatabase
import by.itacademy.training.task8.ui.activity.ErrorInformer

class ViewModelFactory(
    private val application: Application,
    private val informer: ErrorInformer
) : ViewModelProvider.Factory {

    private val dao = ContactsDatabase.getContactsDatabase(application).contactsDao()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            return ContactsViewModel(application, dao, informer) as T
        }
        throw IllegalArgumentException(application.resources.getString(R.string.unknown_class_error))
    }
}
