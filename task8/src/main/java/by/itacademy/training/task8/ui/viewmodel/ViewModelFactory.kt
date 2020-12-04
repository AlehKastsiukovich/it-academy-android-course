package by.itacademy.training.task8.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.itacademy.training.task8.R
import by.itacademy.training.task8.ui.view.ErrorInformer

class ViewModelFactory(
    private val application: Application,
    private val informer: ErrorInformer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            return ContactsViewModel(application, informer) as T
        }
        throw IllegalArgumentException(application.resources.getString(R.string.unknown_class_error))
    }
}
