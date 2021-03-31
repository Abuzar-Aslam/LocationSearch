package com.abuzar.locationsearch.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.base.BaseFragment
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.SearchCityFragmentBinding
import kotlinx.android.synthetic.main.search_city_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController


class SearchCityFragment : BaseFragment<SearchCityFragmentBinding>(),
    SearchView.OnQueryTextListener,SearchCityNavigation {


    private val searchViewModel: SearchCityViewModel by viewModel()
    lateinit var queryString: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        searchViewModel.setNavigator(this)
        recyclerViewList.apply {

            layoutManager = LinearLayoutManager(context)
            val decoration = DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
            addItemDecoration(decoration)
            adapter = searchViewModel.getAdapter()
        }

        registerLiveDataForCityList()
    }

    private fun registerLiveDataForCityList() {
        searchViewModel.getCityMutableLiveData()
            .observe(viewLifecycleOwner, Observer<ArrayList<CityModel>> {
                progressBar.visibility = View.GONE
                if (it != null) {
                    searchViewModel.setAdapterData(it)
                }
            })
    }


    override fun getLayoutId(): Int {
        return R.layout.search_city_fragment
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
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            return false
        } else {
            progressBar.visibility= View.VISIBLE
            queryString = newText
            searchViewModel.searchCities(newText)
            return true
        }
    }

    override fun launchMapFragment() {

        findNavController().navigate(R.id.action_cityList_to_mapFragment)
    }

}