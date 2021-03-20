package com.cesarschool.android_weather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cesarschool.android_weather.R
import com.cesarschool.android_weather.databinding.ActivityMainBinding
import com.cesarschool.android_weather.ui.favorite.FavoriteFragment
import com.cesarschool.android_weather.ui.search.SearchFragment
import com.cesarschool.android_weather.ui.settings.SettingsPreferencesFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val searchFragment = SearchFragment()
    private val favoriteFragment = FavoriteFragment()
    private val settingsFragment = SettingsPreferencesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_search -> setFragment(searchFragment)
                R.id.menu_favorite -> setFragment(favoriteFragment)
                R.id.menu_settings -> setFragment(settingsFragment)
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.menu_search
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.containerView.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}