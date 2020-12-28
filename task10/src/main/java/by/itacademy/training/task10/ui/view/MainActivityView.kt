package by.itacademy.training.task10.ui.view

import android.content.ServiceConnection
import by.itacademy.training.task10.model.entity.Song

interface MainActivityView {

    fun bindService(connection: ServiceConnection)

    fun initSeekBar()

    fun showTrackList(list: List<Song>)
}
