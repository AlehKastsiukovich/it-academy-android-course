package by.itacademy.training.task10.ui.presenter

import android.app.Application
import by.itacademy.training.task10.model.entity.Song
import by.itacademy.training.task10.ui.adapter.OnSongClickListener

interface MainActivityPresenter : OnSongClickListener {

    fun onPlayButton()

    fun onStopButton()

    fun duration(): Int

    fun currentState(): Int

    fun seekTo(progress: Int): Unit

    fun fetchSongs(application: Application): List<Song>
}
