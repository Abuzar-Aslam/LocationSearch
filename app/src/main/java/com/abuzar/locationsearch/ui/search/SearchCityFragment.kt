package com.abuzar.locationsearch.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.base.BaseFragment
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.SearchCityFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel


class SearchCityFragment : BaseFragment<SearchCityFragmentBinding>(),
    SearchView.OnQueryTextListener, SearchCityNavigation {


    private val searchViewModel: SearchCityViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        activity?.title = getString(R.string.searchScreenTitle)
        searchViewModel.setNavigator(this)
        getBinding().recyclerViewList.apply {

            layoutManager = LinearLayoutManager(context)
            val decoration = DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
            addItemDecoration(decoration)
            adapter = searchViewModel.getAdapter()
        }

        Log.e("Abuzar", "ONVIEW CREATED")
        registerLiveDataForCityList()
    }

    override fun onResume() {
        super.onResume()
        val activity = activity as AppCompatActivity?
        if (activity != null) {
            activity.supportActionBar!!.show()
        }

        Log.e("Abuzar", "Come to ONRESUME")
    }

    private fun registerLiveDataForCityList() {
        searchViewModel.getCityMutableLiveData()
            .observe(viewLifecycleOwner, Observer<ArrayList<CityModel>> {
                getBinding().progressBar.visibility = View.GONE
                if (it != null) {
                    searchViewModel.setAdapterData(it)
                }
            })
    }


    override fun getLayoutId(): Int {
        return R.layout.search_city_fragment
    }

    override fun getViewModel(): ViewModel? {
        return searchViewModel
    }


    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
        val mSearchMenuItem = menu.findItem(R.id.action_search)
        val searchView = mSearchMenuItem.actionView as androidx.appcompat.widget.SearchView
        searchView.isIconified = true
        searchView.setOnQueryTextListener(this)
        searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        searchView.isFocusable = true
        searchViewModel.getQueryString()?.let {

            mSearchMenuItem.expandActionView()
            searchView.onActionViewExpanded()
            searchView.setQuery(it, true)
        }
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            return false
        } else {
            getBinding().progressBar.visibility = View.VISIBLE
            searchViewModel.setQueryString(newText)
            searchViewModel.searchCities()
            return true
        }
    }

    override fun launchMapFragment(cityModel: CityModel) {

        val directions = SearchCityFragmentDirections.actionCityListToMapFragment(cityModel)
        findNavController().navigate(directions)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchViewModel.onDestroy()
    }

}