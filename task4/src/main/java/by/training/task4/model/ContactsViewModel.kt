package by.training.task4.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.training.task4.entity.Contact

class ContactsViewModel : ViewModel() {
    private val contacts = MutableLiveData<List<Contact>>()
}

fun <T> MutableLiveData<List<T>>.add(item: T) {
    val updatedItems = this.value as ArrayList
    updatedItems.add(item)
    this.value = updatedItems
}
