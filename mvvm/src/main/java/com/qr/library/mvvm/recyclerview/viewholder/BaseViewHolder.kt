package com.qr.library.mvvm.recyclerview.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<DB : ViewDataBinding>(val binding: DB) :
    RecyclerView.ViewHolder(binding.root) {

}