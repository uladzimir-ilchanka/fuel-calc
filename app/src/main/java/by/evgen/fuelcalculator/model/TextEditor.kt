package by.evgen.fuelcalculator.model

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import by.evgen.fuelcalculator.R
import by.evgen.fuelcalculator.databinding.FragmentFuelConsumptionBinding
import by.evgen.fuelcalculator.viewmodel.FuelConsumptionViewModel

class TextEditor(
    private val context: Context,
    private val binding: FragmentFuelConsumptionBinding,
    private val viewModel: FuelConsumptionViewModel,
    private val constant: String
) { //text works
    fun createString(array: Array<Double>, constant: String): String { //creates string for display
        var resultString = ""
        when (constant) {
            Constants.ONE_HUNDRED_FUEL_CONSUMPTION -> resultString = oneHundredCreateString(array)
            Constants.COMMON_FUEL_CONSUMPTION -> resultString = commonConsumptionCreateString(array)
        }
        return resultString
    }

    fun setTextWatcher(editText: EditText) { //set watcher on text changed and refresh string result if needs
        editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                getDoubleFromEditText(s, editText)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun getDoubleFromEditText(
        s: CharSequence,
        editText: EditText
    ) { //read string from edit text and makes it to string
        if (s.isNotEmpty()) {
            var withoutDot = s.toString()
            if (s[s.length - 1] == '.') { //if dot is the last symbol, removes it to avoid mistakes in calculation
                withoutDot = s.subSequence(0, s.length - 1).toString()
            }
            if (editText == binding.fuelUsedEditText) {
                viewModel.sendToCalculate(1, withoutDot.toDouble(), constant)
            } else {
                if (editText == binding.distanceEditText) {
                    viewModel.sendToCalculate(2, withoutDot.toDouble(), constant)
                } else {
                    if (editText == binding.priceEditText) {
                        viewModel.sendToCalculate(3, withoutDot.toDouble(), constant)
                    }
                }
            }
        } else {
            resetEditText(editText)
        }
    }

    private fun resetEditText(editText: EditText) { //if sequence is empty, makes field empty for removing string from ui
        if (editText == binding.priceEditText) {
            viewModel.sendToCalculate(3, 0.0, constant)
        } else {
            if (editText == binding.distanceEditText) {
                viewModel.sendToCalculate(2, 0.0, constant)
            } else {
                if (editText == binding.fuelUsedEditText) {
                    viewModel.sendToCalculate(1, 0.0, constant)
                }
            }
        }
    }

    private fun oneHundredCreateString(array: Array<Double>): String { //makes string for one hundred consumption calculation
        var resultString = ""
        if (array[0] != 0.0 && array[1] != 0.0 && array[2] != 0.0) {
            resultString =
                "${context.getString(R.string.fuel_consumption_per_100)} <b>${array[0]}</b> " +
                        "<br>${context.getString(R.string.money_spent_total)} <b>${array[1]}</b>" +
                        "<br>${context.getString(R.string.money_spent_per_100)} <b>${array[2]}</b>"
        } else {
            if (array[0] != 0.0) {
                resultString =
                    "${context.getString(R.string.fuel_consumption_per_100)} <b>${array[0]}</b>"
            }
        }
        return resultString
    }

    private fun commonConsumptionCreateString(array: Array<Double>): String { //makes string for common consumption calculation
        var resultString = ""
        if (array[0] != 0.0 && array[1] != 0.0 && array[2] != 0.0) {
            resultString =
                "${context.getString(R.string.total_fuel_used)} <b>${array[0]}</b> " +
                        "<br>${context.getString(R.string.cost_of_the_trip)} <b>${array[1]}</b>" +
                        "<br>${context.getString(R.string.cost_for_100_km)} <b>${array[2]}</b>"
        } else {
            if (array[0] != 0.0) {
                resultString =
                    "${context.getString(R.string.total_fuel_used)} <b>${array[0]}</b>"
            }
        }
        return resultString
    }
}