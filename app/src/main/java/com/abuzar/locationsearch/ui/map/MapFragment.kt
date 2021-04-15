package com.abuzar.locationsearch.ui.map

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.base.BaseFragment
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.android.viewmodel.ext.android.viewModel


class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    val args: MapFragmentArgs by navArgs()
    private lateinit var cityModel: CityModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cityModel = args.CityModel
        getBinding().toolbarId.setNavigationOnClickListener { activity?.onBackPressed() }
        getBinding().toolbarId.title = cityModel.formattedTitle()
        getBinding().cityTitle.text = cityModel.formattedTitle()
        getBinding().citySubTitle.text = cityModel.coordinates.formattedLatLng()
        val activity = activity as AppCompatActivity?
        if (activity != null) {
            activity.supportActionBar!!.hide()
        }
        getBinding().mapView.onCreate(savedInstanceState)
        getBinding().mapView.onResume()
        getBinding().mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {

        map?.let {
            googleMap = it

            val cityLocation = LatLng(
                cityModel.coordinates.latitude,
                cityModel.coordinates.longitude
            )
            googleMap.addMarker(
                MarkerOptions().position(cityLocation)
                    .title(cityModel.formattedTitle())
            )
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cityLocation, 10f))

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_map
    }

    override fun getViewModel(): ViewModel? {
        return null
    }

}