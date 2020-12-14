package by.itacademy.training.task9mvvm.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.training.task9mvvm.R
import by.itacademy.training.task9mvvm.databinding.CityItemBinding
import by.itacademy.training.task9mvvm.model.dto.db.City

class CityAdapter(
    private val onCityClickListener: OnCityClickListener
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cityList = mutableListOf<City>()
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

    fun addCities(cities: List<City>) {
        cityList.clear()
        cityList.addAll(cities)
        notifyDataSetChanged()
    }

    class CityViewHolder(
        private val itemView: View,
        private val binding: CityItemBinding,
        private val onCityClickListener: OnCityClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(city: City) {
            binding.cityNameTextView.text = city.name
            itemView.setOnClickListener { onCityClickListener.onCityClick(city) }
        }
    }
}
