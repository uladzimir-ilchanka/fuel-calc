package by.evgen.fuelcalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.evgen.fuelcalculator.model.Calculator

class FuelConsumptionViewModel : ViewModel() {
    val list = MutableLiveData<Array<Double>>()
    private var calculator: Calculator = Calculator()
    //1 - used fuel
    //2 - distance
    //3 - price

    fun sendToCalculate(count: Int, data: Double, constant: String) {
        when (count) {
            1 -> calculator.fuel = data
            2 -> calculator.distance = data
            3 -> calculator.price = data
        }
        list.value = calculator.calculate(constant)
    }
}