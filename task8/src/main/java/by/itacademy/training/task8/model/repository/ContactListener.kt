package by.itacademy.training.task8.model.repository

import by.itacademy.training.task8.entity.Contact
import by.itacademy.training.task8.util.Event

interface ContactListener {
    fun invoke(event: Event<List<Contact>>)
}