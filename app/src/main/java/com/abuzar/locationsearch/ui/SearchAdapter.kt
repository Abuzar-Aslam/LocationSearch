package com.abuzar.locationsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abuzar.locationsearch.R
import com.abuzar.locationsearch.data.CityModel

class SearchAdapter : ListAdapter<CityModel, SearchAdapter.CityViewHolder>(DifferentUtil) {


    private var cityModelList = ArrayList<CityModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
       return  CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.city_item,parent,false))
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


    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.title)
        private val tvSubTitle: TextView = view.findViewById(R.id.subTitle)

        fun bind(cityModel: CityModel) {

            tvTitle.text = cityModel.cityName
            tvSubTitle.text = cityModel.country
        }

    }

}

internal object DifferentUtil : DiffUtil.ItemCallback<CityModel>() {
    override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel) = oldItem == newItem

    override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
        return false
    }
}