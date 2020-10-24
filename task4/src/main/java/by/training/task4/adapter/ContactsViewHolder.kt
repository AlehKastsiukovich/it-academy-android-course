package by.training.task4.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.training.task4.entity.Contact
import kotlinx.android.synthetic.main.contact_item.view.*

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
