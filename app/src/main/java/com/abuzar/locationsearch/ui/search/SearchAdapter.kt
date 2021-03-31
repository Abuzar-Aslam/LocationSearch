package com.abuzar.locationsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.CityItemBinding
import com.abuzar.locationsearch.utils.PROPERTY_CITY_MODEL

class SearchAdapter(val searchCityNavigation: SearchCityNavigation) : ListAdapter<CityModel, SearchAdapter.CityViewHolder>(
    DifferentUtil
) {


    private var cityModelList = ArrayList<CityModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CityItemBinding.inflate(layoutInflater,parent,false)
        binding.callBack = this

       return  CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cityModelList[position])
    }

    override fun getItemCount(): Int {
        return cityModelList?.size ?: 0
    }

    fun setCitiesList(cityModelList: ArrayList<CityModel>) {
        this.cityModelList = cityModelList
        notifyDataSetChanged()
    }

    fun onItemClick(city: CityModel) {
        searchCityNavigation.launchMapFragment(city)
    }


    class CityViewHolder(val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(city : CityModel){
            binding.cityData=city
            binding.executePendingBindings()
        }
    }

}

internal object DifferentUtil : DiffUtil.ItemCallback<CityModel>() {
    override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
        return false
    }
}