package com.cesarschool.android_weather.ui.search

import android.content.Intent
import androidx.fragment.app.Fragment
import com.cesarschool.android_weather.databinding.FragmentSearchBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesarschool.android_weather.data.local.model.CityRaw
import com.cesarschool.android_weather.extension.toPx
import com.cesarschool.android_weather.preferences.SharedPreferences
import com.cesarschool.android_weather.ui.forecast.ForecastActivity
import com.cesarschool.android_weather.util.MarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by sharedViewModel()
    private lateinit var binding: FragmentSearchBinding

    private val searchAdapter by lazy {
        SearchAdapter {
            city ->
            val intent = Intent(this.requireContext(), ForecastActivity::class.java)
            city.apply {
                val cityRaw = CityRaw(
                    this.id,
                    this.name,
                    this.country.name,
                    this.main.temperature,
                    SharedPreferences.getTempUnitSearched(),
                    city.weathers[0].icon
                )
                intent.putExtra("cityRaw", cityRaw)
                startActivity(intent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        binding.rvCities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
            addItemDecoration(MarginItemDecoration(16.toPx()))
        }

        binding.btnSearch.setOnClickListener {
            viewModel.findCity(binding.edtSearchQuery.text.toString(), searchAdapter)
            SharedPreferences.updateTempUnitSearched(requireContext())
        }
    }
}