package by.itacademy.training.task9mvvm.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import by.itacademy.training.task9mvvm.R
import by.itacademy.training.task9mvvm.databinding.DialogBinding
import by.itacademy.training.task9mvvm.model.dto.db.City

class AddCityDialogFragment(private val addListener: CityAddListener) : DialogFragment() {

    private lateinit var binding: DialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bindLayout()
        return createDialogBuilder().create()
    }

    private fun createDialogBuilder(): AlertDialog.Builder {
        return AlertDialog.Builder(activity).apply {
            setTitle(resources.getString(R.string.add_city))
            setView(binding.root)
            setPositiveButton(
                resources.getString(R.string.confirm)
            ) { _, _ ->
                val cityName = readCityNameFromDialog()
                addListener.onCityAdd(City(cityName))
            }
            setNegativeButton(resources.getString(R.string.cancel)) { _, _ -> }
        }
    }

    private fun readCityNameFromDialog() = binding.cityNameEditText.text.toString()

    private fun bindLayout() {
        binding = DialogBinding.inflate(LayoutInflater.from(context))
    }
}
