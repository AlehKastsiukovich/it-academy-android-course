package by.itacademy.training.task10.ui.presenter

import by.itacademy.training.task10.model.entity.Song
import by.itacademy.training.task10.ui.adapter.OnSongClickListener

interface MainActivityPresenter : OnSongClickListener {

    fun onPlayButton()

    fun onStopButton()

    fun duration(): Int

    fun currentState(): Int

    fun seekTo(progress: Int): Unit

    fun fetchSongs(): List<Song>

    fun onNextButton()

    fun onPreviousButton()
}
