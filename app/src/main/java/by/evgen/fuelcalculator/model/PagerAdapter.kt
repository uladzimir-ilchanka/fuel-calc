package by.evgen.fuelcalculator.model

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import by.evgen.fuelcalculator.R
import by.evgen.fuelcalculator.view.CommonConsumptionFragment
import by.evgen.fuelcalculator.view.OneHundredConsumptionFragment

class PagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CommonConsumptionFragment()
            }
            1 -> {
                OneHundredConsumptionFragment()
            }
            else -> {
                CommonConsumptionFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return context.getString(R.string.range_calculation)
            }
            1 -> {
                return context.getString(R.string.fuel_consumption)
            }
        }
        return super.getPageTitle(position)
    }
}