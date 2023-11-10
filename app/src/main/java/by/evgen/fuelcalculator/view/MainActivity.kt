package by.evgen.fuelcalculator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.evgen.fuelcalculator.R
import by.evgen.fuelcalculator.databinding.ActivityMainBinding
import by.evgen.fuelcalculator.model.PagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        tabLayout = binding.tabLayout
        setContentView(binding.root)
        setViewPager()
    }

    private fun setViewPager() {
        val viewPager = binding.viewPager
        viewPager.adapter = PagerAdapter(supportFragmentManager, this)

        val tabLayout = binding.tabLayout
        tabLayout.setupWithViewPager(viewPager)
    }
}