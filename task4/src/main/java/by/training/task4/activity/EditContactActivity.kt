package by.training.task4.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.training.task4.R
import by.training.task4.entity.Contact
import kotlinx.android.synthetic.main.activity_edit_contact.*

class EditContactActivity : AppCompatActivity() {

    private var currentContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        currentContact = getEditableContact()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> sendUpdatedDataToMainActivity()
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
        val editContactActivityIntent = Intent(this, MainActivity::class.java)
        val position = intent?.extras?.getInt(ITEM_POSITION)

        getUpdatedDataFromFields()

        editContactActivityIntent.putExtra(CONTACT_EXTRAS, currentContact)
        editContactActivityIntent.putExtra(ITEM_POSITION, position)
        setResult(RESULT_OK, editContactActivityIntent)
        finish()
    }

    private fun getUpdatedDataFromFields() {
        currentContact?.contactName = nameEditText.text.toString()
        currentContact?.contact = contactEditText.text.toString()
    }
}
