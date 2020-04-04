package com.qr.library.mvvm.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.qr.library.mvvm.recyclerview.viewholder.BaseViewHolder

abstract class BaseAdapter<T, DB : ViewDataBinding> : RecyclerView.Adapter<BaseViewHolder<DB>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DB>(inflater, layoutId(), parent, false)
        return BaseViewHolder(binding)
    }

    protected abstract fun layoutId(): Int
}