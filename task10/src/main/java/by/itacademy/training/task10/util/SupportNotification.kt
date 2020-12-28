package by.itacademy.training.task10.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import by.itacademy.training.task10.R
import by.itacademy.training.task10.receiver.MediaPlayerActionReceiver

class SupportNotification(private val service: Service) {

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = with(service) {
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                NotificationChannel(CHANNEL_ID, name, importance)
            }
            getNotificationManager().createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification =
        NotificationCompat
            .Builder(service, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_music_note_24)
            .setContentTitle("Title")
            .setContentText("Context")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(
                R.drawable.ic_baseline_skip_next_24,
                BUTTON_NEXT,
                createNextTrackPendingIntent()
            )
            .addAction(
                R.drawable.ic_baseline_skip_previous_24,
                BUTTON_PREVIOUS,
                createPreviousTrackPendingIntent()
            )
            .build()

    fun startNotification() {
        getNotificationManager().notify(NOTIFICATION_ID, createNotification())
    }

    private fun createNextTrackPendingIntent(): PendingIntent {
        val intent = Intent(service, MediaPlayerActionReceiver::class.java).apply {
            action = NEXT_TRACK_INTENT
        }
        return PendingIntent.getBroadcast(
            service,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun createPreviousTrackPendingIntent(): PendingIntent {
        val intent = Intent(service, MediaPlayerActionReceiver::class.java).apply {
            action = PREVIOUS_TRACK_INTENT
        }
        return PendingIntent.getBroadcast(
            service,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun getNotificationManager(): NotificationManager =
        service.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val NOTIFICATION_ID = 99
        const val CHANNEL_ID = "NOTIFICATION_CHANNEL"
        const val NEXT_TRACK_INTENT = "nextTrack"
        const val PREVIOUS_TRACK_INTENT = "previousTrack"
        const val BUTTON_NEXT = "NEXT"
        const val BUTTON_PREVIOUS = "PREVIOUS"
    }
}
