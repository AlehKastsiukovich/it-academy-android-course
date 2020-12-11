package by.itacademy.training.task9mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.task9mvvm.R
import by.itacademy.training.task9mvvm.databinding.HourTemperatureItemBinding
import by.itacademy.training.task9mvvm.model.entity.HourTemperature
import javax.inject.Inject

class TemperatureAdapter @Inject constructor() : RecyclerView.Adapter<TemperatureAdapter.TemperatureItemViewHolder>() {

    private lateinit var binding: HourTemperatureItemBinding

    private val hourTemperatureList = mutableListOf<HourTemperature>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperatureItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.hour_temperature_item, parent, false)
        binding = HourTemperatureItemBinding.bind(view)
        return TemperatureItemViewHolder(view, binding)
    }

    override fun onBindViewHolder(holder: TemperatureItemViewHolder, position: Int) {
        holder.bind(hourTemperatureList[position])
    }

    override fun getItemCount() = hourTemperatureList.size

    fun addElements(list: List<HourTemperature>) {
        hourTemperatureList.clear()
        hourTemperatureList.addAll(list)
        notifyDataSetChanged()
    }

    class TemperatureItemViewHolder @Inject constructor(
        private val itemView: View,
        private val binding: HourTemperatureItemBinding
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(hourTemperature: HourTemperature) {
            binding.itemTemperatureTextView.text = hourTemperature.celsiusTemperature.toString()
            binding.timeTextView.text = hourTemperature.time
        }
    }
}
