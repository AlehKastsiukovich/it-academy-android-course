package by.itacademy.training.task10.ui.view

import android.content.ServiceConnection

interface MainActivityView {

    fun bindService(connection: ServiceConnection)

    fun initSeekBar()
}
