package com.abuzar.locationsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.databinding.CityItemBinding

class SearchAdapter : ListAdapter<CityModel, SearchAdapter.CityViewHolder>(DifferentUtil) {


    private var cityModelList = ArrayList<CityModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CityItemBinding.inflate(layoutInflater)


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