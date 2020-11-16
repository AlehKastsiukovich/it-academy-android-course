package by.training.task7.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.training.task7.adapter.ContactsAdapter
import by.training.task7.databinding.ActivityMainBinding
import by.training.task7.entity.Contact
import by.training.task7.model.ContactsViewModel
import java.util.*

const val EXTRAS_CONTACT_OBJECT = "Object"
const val ITEM_POSITION = "Position"
const val CONTACT_TO_EDIT_CONTACT_EXTRAS = "Contact"
const val CONTACT_EXTRAS = "Editable name"
const val EDIT_OPERATION_EXTRAS = "Edit"
const val REMOVE_OPERATION_EXTRAS = "Remove"
const val CREATE_CONTACT_REQUEST_CODE = 9999
const val EDIT_CONTACT_REQUEST_CODE = 10000
const val OPERATION_TYPE = 1
const val CURRENT_EDIT_TEXT = "Current search text"

class MainActivity : AppCompatActivity(), ContactsAdapter.OnItemClickListener {

    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var model: ContactsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProviders.of(this).get(ContactsViewModel::class.java)

        setFloatingActionButton()
        setAdaptersProperties()
        observeContactsChanged()
        addContactSearcher()
        getSearchingText(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        binding.noContactsMessage.visibility =
            if (model.contactsLiveData.value == null || (model.contactsLiveData.value)?.size == 0) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            CREATE_CONTACT_REQUEST_CODE -> addContact(data)
            EDIT_CONTACT_REQUEST_CODE -> preferContactOperation(data)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putString(CURRENT_EDIT_TEXT, binding.searchContactEditText.text.toString())
    }

    private fun getSearchingText(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            binding.searchContactEditText.setText(it.getString(CURRENT_EDIT_TEXT))
        }
    }

    private fun setFloatingActionButton() {
        binding.floatingActionButton.setOnClickListener {
            startActivityForResult(
                Intent(this, AddContactActivity::class.java), CREATE_CONTACT_REQUEST_CODE
            )
        }
    }

    private fun setAdaptersProperties() {
        contactsAdapter = ContactsAdapter(this)
        binding.recyclerView.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun observeContactsChanged() {
        model.contactsLiveData.observe(
            this,
            Observer {
                contactsAdapter.setData(it)
            }
        )
    }

    private fun addContact(intent: Intent?) {
        val contactObject = intent?.extras?.getParcelable<Contact>(EXTRAS_CONTACT_OBJECT)
        contactObject?.let {
            model.add(it)
        }
    }

    private fun removeContact(intent: Intent?) {
        val position = intent?.extras?.getInt(ITEM_POSITION)
        position?.let {
            model.delete(position)
        }
    }

    private fun editContact(intent: Intent?) {
        val contactObject = intent?.extras?.getParcelable<Contact>(CONTACT_EXTRAS)
        val position = intent?.extras?.getInt(ITEM_POSITION)

        if (contactObject != null && position != null) {
            model.edit(contactObject, position)
        }
    }

    private fun preferContactOperation(intent: Intent?) {
        if (intent?.extras?.getInt(REMOVE_OPERATION_EXTRAS) == 0) {
            editContact(intent)
        } else {
            removeContact(intent)
        }
    }

    private fun addContactSearcher() {
        binding.searchContactEditText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchedContacts = mutableListOf<Contact>()
                if (!s.isNullOrEmpty()) {
                    for (contact in model.contactsLiveData.value as MutableList<Contact>) {
                        if (contact.contactName.toLowerCase(Locale.ROOT)
                            .contains(s.toString().toLowerCase(Locale.ROOT))
                        ) {
                            searchedContacts.add(contact)
                        }
                    }
                    contactsAdapter.setData(searchedContacts)
                } else {
                    contactsAdapter.setData(model.contactsLiveData.value as List<Contact>)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onItemClick(contact: Contact, position: Int) {
        val intent = Intent(this, EditContactActivity::class.java).apply {
            putExtra(CONTACT_TO_EDIT_CONTACT_EXTRAS, contact)
            putExtra(ITEM_POSITION, position)
        }
        startActivityForResult(intent, EDIT_CONTACT_REQUEST_CODE)
    }
}
