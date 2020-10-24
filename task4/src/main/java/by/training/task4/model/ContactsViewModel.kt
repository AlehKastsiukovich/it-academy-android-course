package by.training.task4.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.training.task4.entity.Contact

class ContactsViewModel : ViewModel() {
    private val contacts = MutableLiveData<MutableList<Contact>>()

    fun getContacts(): MutableLiveData<MutableList<Contact>> {
        return contacts
    }
}

fun <T> MutableLiveData<MutableList<T>>.add(item: T) {
    val updatedItems = this.value ?: mutableListOf()
    updatedItems.add(item)
    this.value = updatedItems
}
