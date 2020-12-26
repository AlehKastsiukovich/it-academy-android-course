package by.training.itacademycourse.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.training.itacademycourse.R
import by.training.itacademycourse.databinding.SongItemBinding
import by.training.itacademycourse.model.entity.Song

class SongAdapter : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private val songList = mutableListOf<Song>()
    private lateinit var binding: SongItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.song_item, parent, false)
        binding = SongItemBinding.bind(view)
        return SongViewHolder(view, binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songList[position])
    }

    override fun getItemCount(): Int = songList.size

    fun addSongs(list: List<Song>) {
        songList.clear()
        songList.addAll(list)
        notifyDataSetChanged()
    }

    class SongViewHolder(
        private val view: View,
        private val binding: SongItemBinding
    ) : RecyclerView.ViewHolder(view) {

        fun bind(song: Song) {
            binding.titleTextView.text = song.title
            binding.artistTextView.text = song.artist
        }
    }
}
