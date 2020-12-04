package by.itacademy.training.task8.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task8.databinding.ActivityMainBinding
import by.itacademy.training.task8.model.entity.Contact
import by.itacademy.training.task8.ui.adapter.ContactsAdapter
import by.itacademy.training.task8.ui.viewmodel.ContactsViewModel
import by.itacademy.training.task8.util.Event
import by.itacademy.training.task8.util.Status
import by.itacademy.training.task8.util.factory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

const val EXTRAS_CONTACT_OBJECT = "Object"
const val ITEM_POSITION = "Position"
const val CONTACT_TO_EDIT_CONTACT_EXTRAS = "Contact"
const val CONTACT_EXTRAS = "Editable name"
const val REMOVE_OPERATION_EXTRAS = "Remove"
const val CURRENT_EDIT_TEXT = "Current search text"
const val EMPTY_STRING = ""
const val CREATE_CONTACT_REQUEST_CODE = 9999
const val EDIT_CONTACT_REQUEST_CODE = 10000
const val OPERATION_TYPE = 1

class MainActivity : AppCompatActivity(), ContactsAdapter.OnItemClickListener, ErrorInformer {

    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var model: ContactsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setFloatingActionButton()
        setAdaptersProperties()
        observeContactsChanged()
        addContactSearcher()
        getSearchingText(savedInstanceState)
        setPreferenceButtonListener()
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

    private fun setUpViewModel() {
        model = ViewModelProvider(this, ViewModelFactory(application = application, informer = this))
            .get(ContactsViewModel::class.java)
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
        setNoContactMessageVisibility()
        model.contacts.observe(
            this,
            Observer {
                setUpVisibility(it)
            }
        )
    }

    private fun setUpVisibility(event: Event<List<Contact>>) {
        with(binding) {
            when (event.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    event.data?.let { list -> contactsAdapter.setData(list) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.ERROR -> Snackbar.make(
                    parentLayout,
                    event.message ?: EMPTY_STRING,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setNoContactMessageVisibility() {
        if (model.contacts.value == null) {
            binding.noContactsMessage.visibility = View.VISIBLE
        } else {
            binding.noContactsMessage.visibility = View.INVISIBLE
        }
    }

    private fun addContact(intent: Intent?) {
        val contactObject = intent?.extras?.getParcelable<Contact>(EXTRAS_CONTACT_OBJECT)
        contactObject?.let {
            model.add(it)
        }
    }

    private fun removeContact(intent: Intent?) {
        val contactObject = intent?.extras?.getParcelable<Contact>(CONTACT_EXTRAS)
        contactObject?.let {
            model.delete(contactObject)
        }
    }

    private fun editContact(intent: Intent?) {
        val contactObject = intent?.extras?.getParcelable<Contact>(CONTACT_EXTRAS)
        contactObject?.let {
            model.update(contactObject)
        }
    }

    private fun preferContactOperation(intent: Intent?) {
        if (intent?.extras?.getInt(REMOVE_OPERATION_EXTRAS) == 0) {
            editContact(intent)
        } else {
            removeContact(intent)
        }
    }

    override fun onItemClick(contact: Contact, position: Int) {
        val intent = Intent(this, EditContactActivity::class.java).apply {
            putExtra(CONTACT_TO_EDIT_CONTACT_EXTRAS, contact)
            putExtra(ITEM_POSITION, position)
        }
        startActivityForResult(intent, EDIT_CONTACT_REQUEST_CODE)
    }

    private fun addContactSearcher() {
        binding.searchContactEditText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchedContacts = mutableListOf<Contact>()
                if (!s.isNullOrEmpty()) {
                    for (contact in model.contacts.value?.data as List<Contact>) {
                        if (contact.contactName.toLowerCase(Locale.ROOT)
                            .contains(s.toString().toLowerCase(Locale.ROOT))
                        ) {
                            searchedContacts.add(contact)
                        }
                    }
                    contactsAdapter.setData(searchedContacts)
                } else {
                    contactsAdapter.setData(model.contacts.value?.data as List<Contact>)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setPreferenceButtonListener() {
        binding.preferenceButton.setOnClickListener {
            startActivity(Intent(this, MultithreadingPreferenceSettingsActivity::class.java))
        }
    }

    override fun inform(message: String?) {
        Snackbar.make(binding.parentLayout, message as CharSequence, Snackbar.LENGTH_LONG).show()
    }
}
