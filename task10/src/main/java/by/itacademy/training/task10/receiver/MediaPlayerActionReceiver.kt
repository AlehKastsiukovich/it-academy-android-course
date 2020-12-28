package by.itacademy.training.task10.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import by.itacademy.training.task10.service.AudioPlayerService
import by.itacademy.training.task10.util.SupportNotification

class MediaPlayerActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { intent ->
            when (intent.action) {
                SupportNotification.NEXT_TRACK_INTENT -> { onNextTrackAction(intent, context) }
                SupportNotification.PREVIOUS_TRACK_INTENT -> { onPreviousTrackAction(intent, context) }
            }
        }
    }

    private fun onNextTrackAction(intent: Intent, context: Context?) {
        context?.run {
            Intent(context, AudioPlayerService::class.java).also {
                it.putExtra(INTENT_EXTRAS, SupportNotification.NEXT_TRACK_INTENT)
                startService(it)
            }
        }
    }

    private fun onPreviousTrackAction(intent: Intent, context: Context?) {
        context?.run {
            Intent(context, AudioPlayerService::class.java).also {
                it.putExtra(INTENT_EXTRAS, SupportNotification.PREVIOUS_TRACK_INTENT)
                startService(it)
            }
        }
    }

    companion object {
        const val INTENT_EXTRAS = "EXTRAS"
    }
}
