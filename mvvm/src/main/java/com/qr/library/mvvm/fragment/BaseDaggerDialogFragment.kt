package com.qr.library.mvvm.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerDialogFragment

abstract class BaseDaggerDialogFragment<DB : ViewDataBinding> : DaggerDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<DB>(inflater, layoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initBinding(binding)
        return binding.root
    }

    @LayoutRes
    protected abstract fun layoutId(): Int
    protected abstract fun initBinding(binding: DB)
}