package com.qr.library.mvvm.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<DB>(this, layoutId())
        binding.lifecycleOwner = this
        initBinding(binding)
    }

    @LayoutRes
    protected abstract fun layoutId(): Int
    protected abstract fun initBinding(binding: DB)
}