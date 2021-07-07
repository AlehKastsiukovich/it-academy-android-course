package by.training.task2.observer

object Observers {

    private val observers = mutableListOf<Observer>()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun deleteObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers(data: String) {
        for (observer in observers) {
            observer.notifyDataChanged(data)
        }
    }
}
