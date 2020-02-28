package com.example.weatherassessment.application

import android.app.Application
import com.example.weatherassessment.data.repository.ForecastRepository
import com.example.weatherassessment.data.network.ApiService
import com.example.weatherassessment.data.network.NetworkConnectionInterceptor
import com.example.weatherassessment.ui.ForecastViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))

        //use operator fun in singleton classes mostly act as a constructor
        bind() from singleton {
            NetworkConnectionInterceptor(
                instance()
            )
        }
        bind() from singleton {
            ApiService(
                instance()
            )
        }
        bind() from singleton {
            ForecastRepository(
                instance()
            )
        }

        bind() from provider { ForecastViewModelFactory(instance()) }
    }
}
