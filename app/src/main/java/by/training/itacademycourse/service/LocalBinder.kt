package by.training.itacademycourse.service

import android.os.IBinder

interface LocalBinder : IBinder {

    fun getService(): AudioPlayerService
}
