package by.itacademy.training.task9mvp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.task9mvp.R
import by.itacademy.training.task9mvp.databinding.CityItemBinding
import by.itacademy.training.task9mvp.model.dto.db.CityDto
import javax.inject.Inject

class CityAdapter @Inject constructor(
    private val onCityClickListener: OnCityClickListener
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cityList = mutableListOf<CityDto>()
    private lateinit var binding: CityItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        binding = CityItemBinding.bind(view)
        return CityViewHolder(view, binding, onCityClickListener)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount() = cityList.size

    fun addCities(cities: List<CityDto>) {
        cityList.clear()
        cityList.addAll(cities)
        notifyDataSetChanged()
    }

    class CityViewHolder(
        private val itemView: View,
        private val binding: CityItemBinding,
        private val onCityClickListener: OnCityClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(city: CityDto) {
            binding.cityNameTextView.text = city.name
            itemView.setOnClickListener { onCityClickListener.onCityClick(city) }
        }
    }
}
