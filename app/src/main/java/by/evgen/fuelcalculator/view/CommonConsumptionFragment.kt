package by.evgen.fuelcalculator.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.evgen.fuelcalculator.R
import by.evgen.fuelcalculator.databinding.FragmentFuelConsumptionBinding
import by.evgen.fuelcalculator.model.Constants
import by.evgen.fuelcalculator.model.TextEditor
import by.evgen.fuelcalculator.viewmodel.FuelConsumptionViewModel
import by.evgen.fuelcalculator.viewmodel.ViewModelFactory

class CommonConsumptionFragment : Fragment() {
    private lateinit var binding: FragmentFuelConsumptionBinding
    private lateinit var textEditor: TextEditor
    private lateinit var viewModel: FuelConsumptionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFuelConsumptionBinding.inflate(layoutInflater)
        binding.fuelTextView.text = requireContext().getString(R.string.average_fuel_consumption)
        viewModel = ViewModelFactory().create(FuelConsumptionViewModel::class.java)
        setTextEditor()
        observeData()
        //setFocus(requireContext(), binding.fuelUsedEditText)
        return binding.root
    }

    private fun observeData() { //observes data and changes string result if needs
        viewModel.list.observe(viewLifecycleOwner) {
            binding.resultTextView.text = Html.fromHtml(createString(it), 0)
        }
    }

    private fun createString(array: Array<Double>): String { //creates string for display
        return textEditor.createString(array, Constants.COMMON_FUEL_CONSUMPTION)
    }

    private fun setTextEditor() { //sets strings for result and textWatcher for editText
        textEditor = TextEditor(requireContext(), binding, viewModel, Constants.COMMON_FUEL_CONSUMPTION)
        textEditor.setTextWatcher(binding.fuelUsedEditText)
        textEditor.setTextWatcher(binding.distanceEditText)
        textEditor.setTextWatcher(binding.priceEditText)
    }
}