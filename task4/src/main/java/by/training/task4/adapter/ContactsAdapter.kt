package by.training.task4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.training.task4.R
import by.training.task4.entity.Contact
import kotlinx.android.synthetic.main.contact_item.view.contactNameTextView
import kotlinx.android.synthetic.main.contact_item.view.contactTextView
import kotlinx.android.synthetic.main.contact_item.view.contactTypeImageView

class ContactsAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

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
            onItemClickListener.onItemClick(contacts[position], position)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun setData(contacts: List<Contact>) {
        this.contacts = contacts as MutableList<Contact>
        notifyDataSetChanged()
    }

    class ContactsViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        private val contactImage = viewItem.contactTypeImageView
        private val contactName = viewItem.contactNameTextView
        private val contactValue = viewItem.contactTextView

        fun bind(contact: Contact) {
            contactImage.setImageResource(contact.imageResource)
            contactName.text = contact.contactName
            contactValue.text = contact.contact
        }
    }

    interface OnItemClickListener {
        fun onItemClick(contact: Contact, position: Int)
    }
}
