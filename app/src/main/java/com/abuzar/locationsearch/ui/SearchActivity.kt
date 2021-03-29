package com.abuzar.locationsearch.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    lateinit var mSearchMenuItem: MenuItem
    lateinit var queryString: String
    private var artistList: List<CityModel>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)


    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

    private fun getAllCities() {
//        searchViewModel.getAllCities()
//            .observe(this,
//                Observer<List<CityModel>> { cities -> adapter.setCitiesList(cities as ArrayList<CityModel>) })
    }



}