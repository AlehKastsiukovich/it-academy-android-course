package by.training.task4.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.training.task4.entity.Contact

class ContactsViewModel : ViewModel() {

    private val contacts = MutableLiveData<MutableList<Contact>>()

    val contactsLiveData: LiveData<List<Contact>>
        get() {
            if (contacts.value == null) {
                contacts.value = mutableListOf()
            }
            return contacts as LiveData<List<Contact>>
        }

    fun add(item: Contact) {
        val updatedItems = contacts.value ?: mutableListOf()
        updatedItems.add(item)
        contacts.value = updatedItems
    }

    fun edit(item: Contact, position: Int) {
        val updatedItems = contacts.value ?: mutableListOf()
        updatedItems[position] = item
        contacts.value = updatedItems
    }

    fun delete(position: Int) {
        val updatedItems = contacts.value ?: mutableListOf()
        updatedItems.remove(updatedItems[position])
        contacts.value = updatedItems
    }
}
