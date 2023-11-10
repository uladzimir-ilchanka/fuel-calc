package by.evgen.fuelcalculator.model

import kotlin.math.roundToInt

class Calculator {
    var fuel = 0.0
        set(value) {
            if (value >= 0.0) field = value
        }
    var distance = 0.0
        set(value) {
            if (value >= 0.0) field = value
        }
    var price = 0.0
        set(value) {
            if (value >= 0.0) field = value
        }

    private val localArray = arrayOf(0.0, 0.0, 0.0)
    //[0] - average consumption per 100 km
    //[1] - total price
    //[2] - price per 100 km

    fun calculate(constant: String): Array<Double> { //returns array of results
        when (constant) {
            Constants.ONE_HUNDRED_FUEL_CONSUMPTION -> countAverageFuelConsumption()
            Constants.COMMON_FUEL_CONSUMPTION -> countTotalFuelConsumption()
        }
        return localArray
    }

    private fun countAverageFuelConsumption(): Array<Double> { //counts average consumption for one hundred km
        if (fuel != 0.0 && distance != 0.0) { //average consumption count
            localArray[0] = (calculateFuelConsumptionPer100Km() * 100).roundToInt() / 100.0
        } else {
            localArray[0] = 0.0
        }
        if (fuel != 0.0 && distance != 0.0 && price != 0.0) { //total price and per 100 km price count
            localArray[1] = (calculateTotalPrice() * 100).roundToInt() / 100.0
            localArray[2] = (calculatePricePer100Km() * 100).roundToInt() / 100.0
        } else {
            localArray[1] = 0.0
            localArray[2] = 0.0
        }
        return localArray
    }

    private fun countTotalFuelConsumption(): Array<Double> { //counts total consumption (totally needs fuel and price for trip)
        if (fuel != 0.0 && distance != 0.0) { //fuel consumption during all the way
            localArray[0] = (calculateTotalFuelConsumption() * 100).roundToInt() / 100.0
        } else {
            localArray[0] = 0.0
        }
        if (fuel != 0.0 && distance != 0.0 && price != 0.0) { //counts price for 100 km and for all trip
            localArray[1] = (calculatePricePer100Km() * 100).roundToInt() / 100.0
            localArray[2] = (calculateTotalPrice() * 100).roundToInt() / 100.0
        }
        return localArray
    }

    private fun calculateTotalPrice(): Double { //total price count
        return fuel * price
    }

    private fun calculateFuelConsumptionPer100Km(): Double { //consumption per 100 km count
        return (fuel / distance) * 100
    }

    private fun calculatePricePer100Km(): Double { //price for 100 km count
        return localArray[0] * price
    }

    private fun calculateTotalFuelConsumption(): Double { //fuel consumption during all the way
        return distance / 100 * fuel
    }
}