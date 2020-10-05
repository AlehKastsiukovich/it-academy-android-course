package by.training.task2.observer

import android.util.Log

const val UPDATE_MESSAGE = "Data list is updated"
const val UPDATE_TAG = "Update"

class DataObserver : Observer {

    override fun update(any: Any?) {
        Log.d(UPDATE_TAG, UPDATE_MESSAGE)
    }
}
