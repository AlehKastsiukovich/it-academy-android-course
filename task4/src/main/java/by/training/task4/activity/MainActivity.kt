package by.training.task4.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.training.task4.R
import by.training.task4.adapter.ContactsAdapter
import by.training.task4.entity.Contact
import by.training.task4.model.ContactsViewModel
import by.training.task4.model.add
import kotlinx.android.synthetic.main.activity_main.*

private const val EXTRAS_CONTACT_OBJECT = "object"
const val CREATE_CONTACT_REQUEST_CODE = 9999

class MainActivity : AppCompatActivity() {

    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var model: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(ContactsViewModel::class.java)

        setFloatingActionButton()
        setAdaptersProperties()

        observeContactsChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            CREATE_CONTACT_REQUEST_CODE -> addContact(data)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun setFloatingActionButton() {
        floatingActionButton.setOnClickListener {
            startActivityForResult(
                Intent(this, AddContactActivity::class.java), CREATE_CONTACT_REQUEST_CODE
            )
        }
    }

    private fun setAdaptersProperties() {
        contactsAdapter = ContactsAdapter()
        recyclerView.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observeContactsChanged() {
        model.getContacts().observe(
            this,
            Observer {
                contactsAdapter.setData(it)
            }
        )
    }

    private fun addContact(intent: Intent?) {
        val contactObject = intent?.extras?.getParcelable<Contact>(EXTRAS_CONTACT_OBJECT)
        contactObject?.let {
            model.getContacts().add(it)
        }
    }
}
