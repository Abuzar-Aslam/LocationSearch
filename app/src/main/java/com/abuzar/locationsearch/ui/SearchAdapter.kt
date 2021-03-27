package com.abuzar.locationsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.data.CityModel

class SearchAdapter : ListAdapter<CityModel, SearchAdapter.CityViewHolder>(DifferentUtil) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
       return  CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(cityModel: CityModel) {

        }

    }

}

internal object DifferentUtil : DiffUtil.ItemCallback<CityModel>() {
    override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
        return false
    }
}