package by.evgen.fuelcalculator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FuelConsumptionViewModel::class.java)) {
            return FuelConsumptionViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}