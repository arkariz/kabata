package com.arrkariz.kabata

import android.app.Application
import com.arrkariz.kabata.di.networkModule
import com.arrkariz.kabata.di.repositoryModule
import com.arrkariz.kabata.di.useCaseModule
import com.arrkariz.kabata.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}