package by.itacademy.training.task10.ui.presenter

import android.app.Application
import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import by.itacademy.training.task10.model.entity.Song
import by.itacademy.training.task10.service.AudioPlayerService
import by.itacademy.training.task10.ui.view.MainActivityView
import by.itacademy.training.task10.util.SupportAudioFile

class MainActivityPresenterImpl(
    private val mainActivityView: MainActivityView,
    private val supportAudioFile: SupportAudioFile
) : MainActivityPresenter {

    private var isServerConnected: Boolean = false
    private var audioPlayerService: AudioPlayerService? = null

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
    }

    override fun duration() = audioPlayerService?.run { duration() } ?: 0

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

    override fun fetchSongs(application: Application): List<Song> =
        supportAudioFile.getMusicFromExternalStorage(application)

    override fun onClick(song: Song) {
        audioPlayerService?.run { play(song.uri) }
    }
}
