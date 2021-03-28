package com.abuzar.locationsearch.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.base.BaseFragment
import com.abuzar.locationsearch.databinding.SearchCityFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SearchCityFragment : BaseFragment<SearchCityFragmentBinding>() {


    private val searchViewModel: SearchCityViewModel by viewModel()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun getLayoutId(): Int {
        return  R.layout.search_city_fragment
    }

    override fun getViewModel(): ViewModel {
        return searchViewModel
    }

}