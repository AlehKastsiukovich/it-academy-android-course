package by.itacademy.training.task8.model.repository

// class ThreadPoolExecutorMultiThreadingRepository(
//    private val contactsDao: ContactsDao
// ) : BaseRepository {
//
//    private val threadPoolExecutor = CustomThreadPoolExecutor()
//
//    override fun getContacts(contactListener: ContactListener) {
//        val task = { contactsDao.getAllContacts() }
//        val finalTask = try {
//            val list = threadPoolExecutor.submit(task)
//            list.get()
//        } catch (exception: Exception) {
//        }
//    }
//
//    override fun insert(contact: Contact, contactListener: ContactListener) {
//        val task = { contactsDao.insertContact(contact) }
//        threadPoolExecutor.submit(task)
//    }
//
//    override fun update(contact: Contact, contactListener: ContactListener) {
//        val task = { contactsDao.updateContact(contact) }
//        threadPoolExecutor.submit(task)
//    }
//
//    override fun delete(contact: Contact, contactListener: ContactListener) {
//        val task = { contactsDao.deleteContact(contact) }
//        threadPoolExecutor.submit(task)
//    }
// }
