package com.abuzar.locationsearch.ui.map

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.base.BaseFragment
import com.abuzar.locationsearch.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val mapViewModel: MapViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBinding().mapView.onCreate(savedInstanceState)
        getBinding().mapView.onResume()
        getBinding().mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {

        map?.let {
            googleMap = it

            // Add a marker in Sydney and move the camera
            val cityLocation = LatLng(mapViewModel.cityModel.coordinates.latitude,mapViewModel.cityModel.coordinates.longitude)
            googleMap.addMarker(MarkerOptions().position(cityLocation).title(mapViewModel.cityModel.cityName+" , "+mapViewModel.cityModel.country))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cityLocation,10f))

        }
    }

    override fun getLayoutId(): Int {
       return R.layout.fragment_map
    }

    override fun getViewModel(): ViewModel {
        return mapViewModel
    }
}