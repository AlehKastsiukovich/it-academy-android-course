package by.training.task4.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.training.task4.R
import by.training.task4.entity.Contact
import kotlinx.android.synthetic.main.activity_add_contact.*

class AddContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            contactEditText.hint = when (checkedId) {
                R.id.phoneNumberRadioButton -> getString(R.string.phone_number)
                else -> getString(R.string.email)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_contact, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.confirmContactButton) {
            saveContact()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveContact() {
        val intent = Intent(this, MainActivity::class.java)
        val contact = createContact()
        intent.putExtra(EXTRAS_CONTACT_OBJECT, contact)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun getContactImageResource() =
        if (phoneNumberRadioButton.isChecked) {
            R.drawable.ic_baseline_contact_phone_24
        } else {
            R.drawable.ic_baseline_contact_mail_24
        }

    private fun createContact(): Contact {
        val contactName = nameEditText.text.toString()
        val contact = contactEditText.text.toString()
        val imageSource = getContactImageResource()
        return Contact(imageSource, contactName, contact)
    }
}
