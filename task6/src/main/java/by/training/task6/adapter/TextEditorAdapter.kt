package by.training.task6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.training.task6.databinding.TextFileItemBinding

class TextEditorAdapter(val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TextEditorAdapter.TextEditorViewHolder>() {

    private var files = mutableListOf<TextFile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextEditorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TextFileItemBinding.inflate(layoutInflater, parent, false)
        return TextEditorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextEditorViewHolder, position: Int) {
        val textFile = files[position]
        holder.bind(textFile)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(textFile)
        }
    }

    override fun getItemCount() = files.size

    fun setFiles(list: List<TextFile>) {
        files = list.toMutableList()
        notifyDataSetChanged()
    }

    fun addFiles(list: List<TextFile>) {
        this.files.addAll(list)
        notifyDataSetChanged()
    }

    class TextEditorViewHolder(private val binding: TextFileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fileName: TextFile) {
            binding.fileName.text = fileName.fileName
        }
    }

    interface OnItemClickListener {
        fun onItemClick(textFile: TextFile)
    }
}
