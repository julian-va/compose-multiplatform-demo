package jva.cloud.democomposemultiplatform

import android.app.Application
import jva.cloud.democomposemultiplatform.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DemoApp)
        }

    }
}