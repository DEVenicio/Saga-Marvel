package com.venicio.sagamarvel

import android.app.Application
import com.venicio.sagamarvel.di.daoModule
import com.venicio.sagamarvel.di.dataSourceModule
import com.venicio.sagamarvel.di.repositoryModule
import com.venicio.sagamarvel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(daoModule)
            modules(dataSourceModule)
        }
    }
}