package by.itacademy.training.task8.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.itacademy.training.task8.databinding.ActivityEditContactBinding
import by.itacademy.training.task8.entity.Contact

class EditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding
    private var currentContact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        currentContact = getEditableContact()

        binding.removeContactButton.setOnClickListener {
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
            binding.nameEditText.setText(contact.contactName)
            binding.contactEditText.setText(contact.contact)
        }
        return contact
    }

    private fun sendUpdatedDataToMainActivity() {
        getUpdatedDataFromFields()
        val editContactActivityIntent = Intent().apply {
            putExtra(CONTACT_EXTRAS, currentContact)
        }
        setResult(RESULT_OK, editContactActivityIntent)
        finish()
    }

    private fun getUpdatedDataFromFields() {
        currentContact?.apply {
            contactName = binding.nameEditText.text.toString()
            contact = binding.contactEditText.text.toString()
        }
    }

    private fun sendDataAboutRemovingContact() {
        val editContactActivityIntent = Intent().apply {
            putExtra(CONTACT_EXTRAS, currentContact)
            putExtra(REMOVE_OPERATION_EXTRAS, OPERATION_TYPE)
        }
        setResult(RESULT_OK, editContactActivityIntent)
        finish()
    }
}
