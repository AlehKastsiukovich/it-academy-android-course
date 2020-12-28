package by.itacademy.training.task10.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import by.itacademy.training.task10.receiver.MediaPlayerActionReceiver
import by.itacademy.training.task10.util.SupportNotification

class AudioPlayerService : Service() {

    private val binder: IBinder = LocalBinder()
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var supportNotification: SupportNotification

    inner class LocalBinder : Binder() {
        fun getService(): AudioPlayerService = this@AudioPlayerService
    }

    override fun onCreate() {
        super.onCreate()
        supportNotification = SupportNotification(this).apply {
            createNotificationChannel()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = binder

    fun play(uri: Uri) {
        mediaPlayer?.reset()
        mediaPlayer = MediaPlayer.create(application, uri).apply { start() }
        supportNotification.startNotification()
    }

    fun pause() = mediaPlayer?.run { pause() }

    fun resume() = mediaPlayer?.run { start() }

    fun duration() = mediaPlayer?.run { duration } ?: 0

    fun currentPosition() = mediaPlayer?.run { currentPosition } ?: 0

    fun seekTo(progress: Int) = mediaPlayer?.run { seekTo(progress) }

    override fun onDestroy() {
        mediaPlayer?.release()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { intent->
            when(intent.extras?.getString(MediaPlayerActionReceiver.INTENT_EXTRAS)) {
                SupportNotification.NEXT_TRACK_INTENT -> resume()
                SupportNotification.PREVIOUS_TRACK_INTENT -> pause()
                else -> {}
            }
        }
        return START_STICKY
    }
}
