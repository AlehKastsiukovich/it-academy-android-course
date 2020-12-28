package by.training.itacademycourse.ui.view

import android.Manifest
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import by.training.itacademycourse.R
import by.training.itacademycourse.databinding.ActivityMainBinding
import by.training.itacademycourse.service.AudioPlayerService

class MainActivity : AppCompatActivity(), MainActivityView {

//    private lateinit var presenter: MainActivityPresenter
//    private lateinit var songAdapter: SongAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("TAG", "onCreate")

//        presenter = MainActivityPresenterImpl(this)
//        initAdapter()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("TAGG", "VERSION_CODES")
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
            } else {
                Log.d("TAGG", "android.os.Build.VERSION")
                getMusic()
            }
        }
    }

    private fun getMusic() {
        Log.d("TAGG", "getMusic")
        val contentResolver = contentResolver
        application.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                val nameIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
                Log.d("TAGG", "nameIndex $nameIndex")
                val artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
                val name = cursor.getString(nameIndex)
                val artist = cursor.getString(artistIndex)
            }
        }
    }

    override fun bindService(connection: ServiceConnection) {
        bindService(
            Intent(this, AudioPlayerService::class.java),
            connection,
            BIND_AUTO_CREATE
        )
    }

//    private fun initAdapter() {
//        songAdapter = SongAdapter()
//        binding.recyclerView.apply {
//            adapter = songAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (checkSelfPermission(
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        getMusic()
                    }
                }
            }
        }
    }
}
