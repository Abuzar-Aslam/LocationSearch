package com.abuzar.locationsearch.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class BindingUtils {

    @BindingAdapter(value = ["setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

}