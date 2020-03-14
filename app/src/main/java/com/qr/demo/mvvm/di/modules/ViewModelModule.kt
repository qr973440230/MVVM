package com.qr.demo.mvvm.di.modules

import com.qr.demo.mvvm.ui.MainViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModelModule {

    @ContributesAndroidInjector
    abstract fun mainViewModel(): MainViewModel

}