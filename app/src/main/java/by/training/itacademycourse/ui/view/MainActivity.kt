package by.training.itacademycourse.ui.view

import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.training.itacademycourse.R
import by.training.itacademycourse.service.AudioPlayerService
import by.training.itacademycourse.ui.presenter.MainActivityPresenter
import by.training.itacademycourse.ui.presenter.MainActivityPresenterImpl

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainActivityPresenterImpl(this)
    }

    override fun bindService(connection: ServiceConnection) {
        bindService(
            Intent(this, AudioPlayerService::class.java),
            connection,
            BIND_AUTO_CREATE
        )
    }
}
