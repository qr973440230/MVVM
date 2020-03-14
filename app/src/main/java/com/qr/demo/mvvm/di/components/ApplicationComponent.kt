package com.qr.demo.mvvm.di.components

import com.qr.demo.mvvm.APP
import com.qr.demo.mvvm.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ARouterInterceptorModule::class,
        ViewModelModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ServiceModule::class]
)
interface ApplicationComponent : AndroidInjector<APP> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<APP> {

    }
}