package com.cesarschool.android_weather.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.cesarschool.android_weather.R

class SettingsPreferencesFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_screen)
    }
}