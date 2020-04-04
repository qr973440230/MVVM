package com.qr.library.mvvm.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.qr.library.mvvm.recyclerview.viewholder.BaseViewHolder

abstract class BasePagingAdapter<T, DB : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BaseViewHolder<DB>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DB>(inflater, layoutId(), parent, false)
        return BaseViewHolder(binding)
    }

    protected abstract fun layoutId(): Int
}