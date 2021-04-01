package com.abuzar.locationsearch.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {


    private lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        getViewModel()?.let {
            binding.setVariable(1, it)
        }
        binding.executePendingBindings()
        return binding.root
    }

    fun getBinding(): B {
        return binding
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int


    protected abstract fun getViewModel(): ViewModel?

    /**
     * finish parent activity
     */
    fun finish() {
        val activity = activity
        if (activity != null) {
            ActivityCompat.finishAfterTransition(activity)
        }
    }


    open fun onBackButtonPressed() {
        //Do Nothing
        Log.d("Abuzar", "Base Fragment Back Button Pressed")
    }

}