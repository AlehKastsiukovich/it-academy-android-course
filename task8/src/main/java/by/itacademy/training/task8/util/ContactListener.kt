package by.itacademy.training.task8.util

import by.itacademy.training.task8.model.entity.Contact

interface ContactListener {

    fun onContactsGetListener(event: Event<List<Contact>>)

    fun onContactsAddError(event: Event<Throwable>)

    fun onContactsDeleteError(event: Event<Throwable>)

    fun onContactsUpdateError(event: Event<Throwable>)
}
