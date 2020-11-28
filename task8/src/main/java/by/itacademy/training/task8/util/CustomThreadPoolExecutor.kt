package by.itacademy.training.task8.util

import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class CustomThreadPoolExecutor : ThreadPoolExecutor(
    CORE_POOL_SIZE,
    MAXIMUM_POOL_SIZE,
    KEEP_ALIVE_TIME,
    TimeUnit.SECONDS,
    SynchronousQueue()
) {
    companion object {
        private const val CORE_POOL_SIZE = 1
        private const val MAXIMUM_POOL_SIZE = 10
        private const val KEEP_ALIVE_TIME: Long = 20
        private const val QUEUE_CAPACITY = 10
    }
}
