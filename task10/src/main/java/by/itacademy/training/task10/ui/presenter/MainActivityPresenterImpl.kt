package by.itacademy.training.task10.ui.presenter

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import by.itacademy.training.task10.model.entity.Song
import by.itacademy.training.task10.service.AudioPlayerService
import by.itacademy.training.task10.ui.view.MainActivityView
import by.itacademy.training.task10.util.SupportAudioFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivityPresenterImpl(
    private val mainActivityView: MainActivityView,
    private val supportAudioFile: SupportAudioFile,
    override val coroutineContext: CoroutineContext = Job() + Dispatchers.Main
) : MainActivityPresenter, CoroutineScope {

    private var isServerConnected: Boolean = false
    private var audioPlayerService: AudioPlayerService? = null
    private var currentTrackPosition: Int = -1
    private val tracks = mutableListOf<Song>()

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as AudioPlayerService.LocalBinder
            audioPlayerService = binder.getService()
            isServerConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServerConnected = false
        }
    }

    init {
        mainActivityView.bindService(connection)
        tracks.addAll(supportAudioFile.getMusicFromExternalStorage())
    }

    override fun duration() = launch {
            audioPlayerService?.run { duration() } ?: 0
    }

    override fun currentState() = audioPlayerService?.run { currentPosition() } ?: 0

    override fun onPlayButton() {
        audioPlayerService?.run { resume() }
    }

    override fun onStopButton() {
        audioPlayerService?.run { pause() }
    }

    override fun seekTo(progress: Int) {
        audioPlayerService?.run { seekTo(progress) }
    }

    override fun fetchSongs(): List<Song> =
        supportAudioFile.getMusicFromExternalStorage()

    override fun onNextButton() {
        if ((currentTrackPosition != -1) && (currentTrackPosition != (tracks.size - 1))) {
            audioPlayerService?.run { play(tracks[currentTrackPosition + 1].uri) }
        }
    }

    override fun onPreviousButton() {
        if ((currentTrackPosition != -1) && (currentTrackPosition != 0)) {
            audioPlayerService?.run { play(tracks[currentTrackPosition - 1].uri) }
        }
    }

    override fun onClick(song: Song, position: Int) {
        currentTrackPosition = position
        audioPlayerService?.run { play(song.uri) }
    }
}
