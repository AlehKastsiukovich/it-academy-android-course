package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.util.Event

interface ContactListener {

    fun fetchContacts(event: Event<List<Contact>>)

    fun addContact(event: Event<List<Contact>>)
}
