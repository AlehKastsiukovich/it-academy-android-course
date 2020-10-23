package by.training.task4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.training.task4.R
import by.training.task4.entity.Contact

class ContactsAdapter : RecyclerView.Adapter<ContactsViewHolder>() {

    private var contacts = mutableListOf<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return when (contacts.size) {
            0 -> 0
            else -> contacts.size
        }
    }

    fun setData(contacts: List<Contact>) {
        this.contacts = contacts as MutableList<Contact>
    }
}
