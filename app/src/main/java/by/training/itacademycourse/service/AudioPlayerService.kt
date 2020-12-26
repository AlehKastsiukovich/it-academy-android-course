package by.training.itacademycourse.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.os.IInterface
import android.os.Parcel
import java.io.FileDescriptor

class AudioPlayerService : Service() {

    private val binder: LocalBinder = LocalBinderImpl()
    private val mediaPlayer = MediaPlayer()

    inner class LocalBinderImpl : LocalBinder {

        override fun getService(): AudioPlayerService = this@AudioPlayerService

        override fun getInterfaceDescriptor(): String? = null

        override fun pingBinder(): Boolean = false

        override fun isBinderAlive(): Boolean = false

        override fun queryLocalInterface(descriptor: String): IInterface? = null

        override fun dump(fd: FileDescriptor, args: Array<out String>?) = Unit

        override fun dumpAsync(fd: FileDescriptor, args: Array<out String>?) = Unit

        override fun transact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean = false

        override fun linkToDeath(recipient: IBinder.DeathRecipient, flags: Int) = Unit

        override fun unlinkToDeath(recipient: IBinder.DeathRecipient, flags: Int): Boolean = false
    }

    override fun onBind(intent: Intent?): IBinder? = binder
}
