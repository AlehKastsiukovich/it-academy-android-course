package by.training.task4.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.training.task4.entity.Contact
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactsViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
    val contactImage = viewItem.contactTypeImageView
    val contactName = viewItem.contactNameTextView
    val contactValue = viewItem.contactTextView

    fun bind(contact: Contact) {
        contactImage.setImageResource(contact.imageResource)
        contactName.text = contact.contactName
        contactValue.text = contact.contact
    }
}