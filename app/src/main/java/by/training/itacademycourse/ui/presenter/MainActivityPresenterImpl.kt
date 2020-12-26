package by.training.itacademycourse.ui.presenter

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import by.training.itacademycourse.service.AudioPlayerService
import by.training.itacademycourse.service.LocalBinder
import by.training.itacademycourse.ui.view.MainActivityView

class MainActivityPresenterImpl(
    private val mainActivityView: MainActivityView
) : MainActivityPresenter {

    private var isServerConnected: Boolean = false
    private var audioPlayerService: AudioPlayerService? = null

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            audioPlayerService = (service as LocalBinder).getService()
            isServerConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServerConnected = false
        }
    }

    init {
        mainActivityView.bindService(connection)
    }

    override fun onPlayButton() {
        TODO("Not yet implemented")
    }

    override fun onStopButton() {
        TODO("Not yet implemented")
    }
}
