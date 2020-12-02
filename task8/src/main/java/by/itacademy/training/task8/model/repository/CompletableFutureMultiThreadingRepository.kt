package by.itacademy.training.task8.model.repository

// class CompletableFutureMultiThreadingRepository(private val contactsDao: ContactsDao) :
//    BaseRepository {
//
//    override fun getContacts(contactListener: ContactListener) {
//        val task = { contactsDao.getAllContacts() }
//        val future = CompletableFuture.supplyAsync(task, CustomThreadPoolExecutor())
//        future.apply {
//            thenAccept { result -> contactListener.invoke(Event(Status.SUCCESS, result, null)) }
//            val x = get()
//        }
//    }
//
//    override fun insert(contact: Contact) {
//        TODO("Not yet implemented")
//    }
//
//    override fun update(contact: Contact) {
//        TODO("Not yet implemented")
//    }
//
//    override fun delete(contact: Contact) {
//        TODO("Not yet implemented")
//    }
// }
