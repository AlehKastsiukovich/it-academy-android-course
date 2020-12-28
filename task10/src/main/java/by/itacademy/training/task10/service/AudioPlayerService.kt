package by.itacademy.training.task10.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import by.itacademy.training.task10.receiver.MediaPlayerActionReceiver
import by.itacademy.training.task10.util.SupportNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AudioPlayerService : Service(), CoroutineScope {

    private val binder: IBinder = LocalBinder()
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var supportNotification: SupportNotification
    private val serviceJob = Job()
    override val coroutineContext: CoroutineContext = serviceJob + Dispatchers.IO

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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        launch {
            intent?.let { intent ->
                when (intent.extras?.getString(MediaPlayerActionReceiver.INTENT_EXTRAS)) {
                    SupportNotification.NEXT_TRACK_INTENT -> resume()
                    SupportNotification.PREVIOUS_TRACK_INTENT -> pause()
                    else -> {
                    }
                }
            }
        }
        return START_STICKY
    }

    fun play(uri: Uri) {
        launch {
            mediaPlayer?.reset()
            mediaPlayer = MediaPlayer.create(application, uri).apply { start() }
            supportNotification.startNotification()
        }
    }

    fun pause() = launch { mediaPlayer?.run { pause() } }

    fun resume() = launch { mediaPlayer?.run { start() } }

    suspend fun duration() = withContext(coroutineContext) { mediaPlayer?.run { duration } ?: 0 }

    suspend fun currentPosition() = withContext(coroutineContext) {
        mediaPlayer?.run { currentPosition } ?: 0
    }

    fun seekTo(progress: Int) = launch { mediaPlayer?.run { seekTo(progress) } }

    override fun onDestroy() {
        mediaPlayer?.release()
        serviceJob.complete()
    }
}
