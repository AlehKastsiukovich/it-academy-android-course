package by.itacademy.training.task10.util

import android.app.Application
import android.content.ContentUris
import android.database.Cursor
import android.provider.MediaStore
import by.itacademy.training.task10.model.entity.Song

class SupportAudioFile {

    fun getMusicFromExternalStorage(application: Application): List<Song> {
        val songs = mutableListOf<Song>()
        application.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
        )?.use { cursor ->
            while (cursor.moveToNext()) {
                songs.add(createSong(cursor))
            }
        }
        return songs
    }

    private fun createSong(cursor: Cursor): Song = with(cursor) {
        val name = getString(getColumnIndex(MediaStore.Audio.Media.TITLE))
        val artist = getString(getColumnIndex(MediaStore.Audio.Media.ARTIST))
        val id = getLong(getColumnIndexOrThrow(MediaStore.Video.Media._ID))
        val uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
        Song(name, artist, uri)
    }
}
