package by.itacademy.training.task9mvvm.util

class Event <out T>(
    private val status: Status,
    private val data: T?,
    private val message: String?
) {
    companion object {

        fun <T> success(data: T): Event<T> = Event(Status.SUCCESS, data, null)

        fun <T> loading(data: T?): Event<T> = Event(Status.LOADING, null, null)

        fun <T> error(data: T?, message: String?): Event<T> = Event(Status.ERROR, null, message)
    }
}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}
