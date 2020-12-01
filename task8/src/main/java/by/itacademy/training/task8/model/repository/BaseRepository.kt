package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact

interface BaseRepository {

    fun getContacts(contactListener: ContactListener)

    fun insert(contact: Contact)

    fun update(contact: Contact)

    fun delete(contact: Contact)
}
