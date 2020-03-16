package com.qr.demo.mvvm.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.qr.demo.mvvm.APP
import com.qr.library.mvvm.cache.CacheManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object ApplicationModule {
    private const val baseUrl: String = "http://183.129.182.186:9206"

    @Singleton
    @Provides
    fun bindApplication(app: APP): Application {
        return app;
    }

    @Singleton
    @Provides
    fun cacheManager(application: Application, gson: Gson): CacheManager {
        return CacheManager(application, gson)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("app_sp", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()

        // TODO: Config Gson
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss")
            .serializeNulls()

        return builder.create()
    }

    @Singleton
    @Provides
    @Named("token_client")
    fun provideTokenOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()


        builder.readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .connectTimeout(3, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        builder.addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    @Named("token_retrofit")
    fun provideTokenRetrofit(@Named("token_client") client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}