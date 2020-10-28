package by.training.task4.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.training.task4.entity.Contact

class ContactsViewModel : ViewModel() {
    private val contacts = MutableLiveData<MutableList<Contact>>()

    fun getContacts(): MutableLiveData<MutableList<Contact>> {
        if (contacts.value == null) {
            contacts.value = mutableListOf()
        }
        return contacts
    }
}

fun <T> MutableLiveData<MutableList<T>>.add(item: T) {
    val updatedItems = this.value ?: mutableListOf()
    updatedItems.add(item)
    this.value = updatedItems
}

fun <T> MutableLiveData<MutableList<T>>.edit(item: T, position: Int) {
    val updatedItems = this.value ?: mutableListOf()
    updatedItems[position] = item
    this.value = updatedItems
}

fun <T> MutableLiveData<MutableList<T>>.delete(position: Int) {
    val updatedItems = this.value ?: mutableListOf()
    updatedItems.remove(updatedItems[position])
    this.value = updatedItems
}

