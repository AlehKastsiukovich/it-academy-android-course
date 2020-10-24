package by.training.task4.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.training.task4.R
import by.training.task4.activity.*
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
        holder.itemView.setOnClickListener {
            editContact(holder.itemView, position)
        }
    }

    override fun getItemCount(): Int {
        return when (contacts.size) {
            0 -> 0
            else -> contacts.size
        }
    }

    fun setData(contacts: List<Contact>) {
        this.contacts = contacts as MutableList<Contact>
        notifyDataSetChanged()
    }

    private fun editContact(view: View, position: Int) {
        val mainActivityContext = view.context as MainActivity
        val intent = Intent(mainActivityContext, EditContactActivity::class.java)
        intent.putExtra(CONTACT_TO_EDIT_CONTACT_EXTRAS, contacts[position])
        intent.putExtra(ITEM_POSITION, position)
        mainActivityContext.startActivityForResult(intent, EDIT_CONTACT_REQUEST_CODE)
    }
}
