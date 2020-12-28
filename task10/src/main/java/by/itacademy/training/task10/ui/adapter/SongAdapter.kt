package by.itacademy.training.task10.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.task10.R
import by.itacademy.training.task10.databinding.SongItemBinding
import by.itacademy.training.task10.model.entity.Song

class SongAdapter(
    private val onSongClickListener: OnSongClickListener
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private val songList = mutableListOf<Song>()
    private lateinit var binding: SongItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.song_item, parent, false)
        binding = SongItemBinding.bind(view)
        return SongViewHolder(view, binding, onSongClickListener)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songList[position], position)
    }

    override fun getItemCount(): Int = songList.size

    fun addSongs(list: List<Song>) {
        songList.clear()
        songList.addAll(list)
        notifyDataSetChanged()
    }

    fun getSongs(): List<Song> = songList

    class SongViewHolder(
        private val view: View,
        private val binding: SongItemBinding,
        private val onSongClickListener: OnSongClickListener
    ) : RecyclerView.ViewHolder(view) {

        fun bind(song: Song, position: Int) {
            view.setOnClickListener { onSongClickListener.onClick(song, position) }
            binding.titleTextView.text = song.title
            binding.artistTextView.text = song.artist
        }
    }
}
