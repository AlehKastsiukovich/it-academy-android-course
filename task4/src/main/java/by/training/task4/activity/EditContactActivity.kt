package by.training.task4.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.training.task4.R
import by.training.task4.entity.Contact
import kotlinx.android.synthetic.main.activity_edit_contact.contactEditText
import kotlinx.android.synthetic.main.activity_edit_contact.nameEditText
import kotlinx.android.synthetic.main.activity_edit_contact.removeContactButton

class EditContactActivity : AppCompatActivity() {

    private var currentContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        currentContact = getEditableContact()

        removeContactButton.setOnClickListener {
            sendDataAboutRemovingContact()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            sendUpdatedDataToMainActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getEditableContact(): Contact? {
        val contact = intent?.extras?.getParcelable<Contact>(CONTACT_TO_EDIT_CONTACT_EXTRAS)
        contact?.let {
            nameEditText.setText(contact.contactName)
            contactEditText.setText(contact.contact)
        }
        return contact
    }

    private fun sendUpdatedDataToMainActivity() {
        getUpdatedDataFromFields()
        val editContactActivityIntent = Intent().apply {
            val position = intent?.extras?.getInt(ITEM_POSITION)
            putExtra(CONTACT_EXTRAS, currentContact)
            putExtra(EDIT_OPERATION_EXTRAS, position)
            putExtra(ITEM_POSITION, position)
        }
        setResult(RESULT_OK, editContactActivityIntent)
        finish()
    }

    private fun getUpdatedDataFromFields() {
        currentContact?.apply {
            contactName = nameEditText.text.toString()
            contact = contactEditText.text.toString()
        }
    }

    private fun sendDataAboutRemovingContact() {
        val editContactActivityIntent = Intent().apply {
            val position = intent?.extras?.getInt(ITEM_POSITION)
            putExtra(ITEM_POSITION, position)
            putExtra(REMOVE_OPERATION_EXTRAS, OPERATION_TYPE)
        }

        setResult(RESULT_OK, editContactActivityIntent)
        finish()
    }
}
