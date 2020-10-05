package by.training.task2.observer

class Observers<T : Observer> : ArrayList<T>() {
    fun notifyDataChanged(any: Any?) {
        val iterator = iterator()

        while (iterator.hasNext()) {
            iterator.next().update(any)
        }
    }
}
