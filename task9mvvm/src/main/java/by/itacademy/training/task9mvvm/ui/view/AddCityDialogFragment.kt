package by.itacademy.training.task9mvvm.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import by.itacademy.training.task9mvvm.R
import by.itacademy.training.task9mvvm.databinding.DialogBinding

class AddCityDialogFragment : DialogFragment() {

    private lateinit var binding: DialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bindLayout()
        val dialogBuilder = AlertDialog.Builder(activity).apply {
            setTitle(resources.getString(R.string.add_city))
            setView(binding.root)
            setPositiveButton(
                resources.getString(R.string.confirm)
            ) { _, _ -> }
            setNegativeButton(resources.getString(R.string.cancel)) { _, _ -> }
        }
        return dialogBuilder.create()
    }

    private fun bindLayout() {
        binding = DialogBinding.inflate(LayoutInflater.from(context))
    }
}
