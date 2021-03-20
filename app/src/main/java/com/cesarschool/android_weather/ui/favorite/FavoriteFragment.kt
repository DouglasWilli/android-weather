package com.cesarschool.android_weather.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cesarschool.android_weather.data.local.model.Favorite
import com.cesarschool.android_weather.data.local.room.WeatherDatabase
import com.cesarschool.android_weather.databinding.FragmentFavoriteBinding
import com.cesarschool.android_weather.extension.toPx
import com.cesarschool.android_weather.util.MarginItemDecoration
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by sharedViewModel()

    private val favoriteAdapter by lazy { FavoriteAdapter {
        unfavorite(it)
    } }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
    }

    private fun initUi() {
        val dao = WeatherDatabase.getInstance(requireContext()).getFavoriteDao()
        favoriteAdapter.submitList(dao.getAll())

        binding.apply {
            btnSearchFavoriteCity.setOnClickListener {
                viewModel.searchFavorits(edtFavoriteCityName.text.toString(), favoriteAdapter)
            }

            rvFavoriteCities.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = favoriteAdapter
                addItemDecoration(MarginItemDecoration(16.toPx()))
            }
        }
    }

    fun unfavorite(it: Favorite ) {
        viewModel.unfavorite(it, favoriteAdapter)
    }
}