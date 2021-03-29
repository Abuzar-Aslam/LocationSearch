package com.abuzar.locationsearch.ui.search
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.base.BaseFragment
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.SearchCityFragmentBinding
import com.abuzar.locationsearch.ui.SearchAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class SearchCityFragment : BaseFragment<SearchCityFragmentBinding>(), SearchView.OnQueryTextListener {


    private val searchViewModel: SearchCityViewModel by viewModel()
    lateinit var queryString: String
    lateinit var adapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        adapter = SearchAdapter()
        val itemDecoration = DividerItemDecoration(getBinding().getRoot().getContext(), DividerItemDecoration.VERTICAL)
        getBinding().list.addItemDecoration(itemDecoration)
        getBinding().list.layoutManager = LinearLayoutManager(activity)
        getBinding().list.adapter = adapter

    }

    override fun getLayoutId(): Int {
        return  R.layout.search_city_fragment
    }

    override fun getViewModel(): ViewModel {
        return searchViewModel
    }


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
        val mSearchMenuItem = menu.findItem(R.id.action_search)

        mSearchMenuItem.isEnabled = true

        val searchView = mSearchMenuItem.actionView as androidx.appcompat.widget.SearchView
        searchView.isIconified = true
        searchView.setOnQueryTextListener(this)
        searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        searchView.isFocusable = true
        super.onCreateOptionsMenu(menu, menuInflater)

        super.onCreateOptionsMenu(menu, menuInflater)
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