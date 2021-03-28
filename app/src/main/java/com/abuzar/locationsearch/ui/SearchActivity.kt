package com.abuzar.locationsearch.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.ActivitySearchBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val searchViewModel: SearchViewModel by viewModel()

    lateinit var adapter: SearchAdapter
    lateinit var mSearchMenuItem: MenuItem
    lateinit var queryString: String
    private var artistList: List<CityModel>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySearchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)
        adapter = SearchAdapter()
        val itemDecoration = DividerItemDecoration(binding.getRoot().getContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(itemDecoration)
        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        binding.list.adapter = adapter
        //getAllCities()

    }


    private fun getAllCities() {
        searchViewModel.getAllCities()
            .observe(this,
                Observer<List<CityModel>> { cities -> adapter.setCitiesList(cities as ArrayList<CityModel>) })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        mSearchMenuItem = menu.findItem(R.id.action_search)

        mSearchMenuItem.setEnabled(true)

        val searchView = mSearchMenuItem.getActionView() as androidx.appcompat.widget.SearchView
        searchView.setIconified(true)
        searchView.setOnQueryTextListener(this)
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH)
        searchView.setFocusable(true)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            return false
        } else {
            queryString = newText
            searchViewModel.searchCities(newText)
                .observe(this,
                    Observer<List<CityModel>> { cities -> adapter.setCitiesList(cities as ArrayList<CityModel>) })
            return true
        }
    }
}