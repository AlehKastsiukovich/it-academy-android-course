package by.training.task7.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.training.task7.R
import by.training.task7.databinding.ActivityAddContactBinding
import by.training.task7.entity.Contact

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            binding.contactEditText.hint = when (checkedId) {
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
        if (binding.phoneNumberRadioButton.isChecked) {
            R.drawable.ic_baseline_contact_phone_24
        } else {
            R.drawable.ic_baseline_contact_mail_24
        }

    private fun createContact(): Contact {
        val contactName = binding.nameEditText.text.toString()
        val contact = binding.contactEditText.text.toString()
        val imageSource = getContactImageResource()
        return Contact(imageSource, contactName, contact)
    }
}
