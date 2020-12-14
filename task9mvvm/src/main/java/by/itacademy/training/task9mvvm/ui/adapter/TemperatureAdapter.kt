package by.itacademy.training.task9mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.task9mvvm.R
import by.itacademy.training.task9mvvm.databinding.HourTemperatureItemBinding
import by.itacademy.training.task9mvvm.model.entity.HourTemperature
import by.itacademy.training.task9mvvm.util.CurrentTemperatureUnitListener
import com.bumptech.glide.Glide
import javax.inject.Inject

class TemperatureAdapter @Inject constructor (
    private val currentTemperatureUnitListener: CurrentTemperatureUnitListener
) : RecyclerView.Adapter<TemperatureAdapter.TemperatureItemViewHolder>() {

    private lateinit var binding: HourTemperatureItemBinding
    private val hourTemperatureList = mutableListOf<HourTemperature>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperatureItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.hour_temperature_item, parent, false)
        binding = HourTemperatureItemBinding.bind(view)
        return TemperatureItemViewHolder(view, binding, currentTemperatureUnitListener)
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
        private val binding: HourTemperatureItemBinding,
        private val currentTemperatureUnitListener: CurrentTemperatureUnitListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(hourTemperature: HourTemperature) {
            binding.itemTemperatureTextView.text =
                currentTemperatureUnitListener.getHourTemperature(hourTemperature)
            binding.timeTextView.text = hourTemperature.time
            setUpImage(itemView, hourTemperature)
        }

        private fun setUpImage(itemView: View, hourTemperature: HourTemperature) {
            Glide.with(itemView)
                .load(hourTemperature.condition.icon)
                .centerCrop()
                .into(binding.hourConditionImage)
        }
    }
}
