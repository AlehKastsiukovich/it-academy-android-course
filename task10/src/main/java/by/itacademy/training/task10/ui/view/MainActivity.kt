package by.itacademy.training.task10.ui.view

import android.Manifest
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.itacademy.training.task10.databinding.ActivityMainBinding
import by.itacademy.training.task10.model.entity.Song
import by.itacademy.training.task10.service.AudioPlayerService
import by.itacademy.training.task10.ui.adapter.SongAdapter
import by.itacademy.training.task10.ui.presenter.MainActivityPresenter
import by.itacademy.training.task10.ui.presenter.MainActivityPresenterImpl
import by.itacademy.training.task10.util.SupportAudioFile
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var presenter: MainActivityPresenter
    private lateinit var songAdapter: SongAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = MainActivityPresenterImpl(this, SupportAudioFile(application))
        initAdapter(presenter)
        checkPermission()
        setUpNavigationButtons()
        setUpSeekBar()
        setUpNextTrackButton()
        setUpPreviousButton()
    }

    private fun initAdapter(presenter: MainActivityPresenter) {
        songAdapter = SongAdapter(presenter)
        binding.recyclerView.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setUpNavigationButtons() {
        with(binding) {
            playButton.setOnClickListener {
                presenter.onPlayButton()
                initSeekBar()
            }
            stopButton.setOnClickListener { presenter.onStopButton() }
        }
    }

    private fun setUpNextTrackButton() {
        binding.nextTrackButton.setOnClickListener { presenter.onNextButton() }
    }

    private fun setUpPreviousButton() {
        binding.nextTrackButton.setOnClickListener { presenter.onPreviousButton() }
    }

    private fun setUpSeekBar() {
        seekBar = binding.seekBar.apply {
            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    presenter.seekTo(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
        }
    }

    override fun bindService(connection: ServiceConnection) {
        bindService(
            Intent(this, AudioPlayerService::class.java),
            connection,
            BIND_AUTO_CREATE
        )
    }

    override fun initSeekBar() {
        seekBar.max = presenter.duration()
        val handler = Handler(Looper.getMainLooper()).also {
            it.postDelayed(
                object : Runnable {
                    override fun run() {
                        try {
                            seekBar.progress = presenter.currentState()
                            it.postDelayed(this, DEFAULT_PROGRESS_MILLIS)
                        } catch (e: Exception) {
                            seekBar.progress = 0
                        }
                    }
                },
                DEFAULT_PROGRESS_MILLIS
            )
        }
    }

    override fun showTrackList(list: List<Song>) {
        songAdapter.addSongs(list)
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_EXTERNAL_STORAGE_CODE
                )
            } else {
                showTrackList(presenter.fetchSongs())
            }
        } else {
            showTrackList(presenter.fetchSongs())
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == READ_EXTERNAL_STORAGE_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    showTrackList(presenter.fetchSongs())
                }
            }
        }
    }

    companion object {
        private const val READ_EXTERNAL_STORAGE_CODE = 1
        private const val DEFAULT_PROGRESS_MILLIS: Long = 1000
    }
}
