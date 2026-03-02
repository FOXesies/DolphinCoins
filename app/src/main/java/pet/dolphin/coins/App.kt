package pet.dolphin.coins

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import pet.dolphin.coins.di.appModule
import pet.dolphin.core.di.coreModule
import pet.dolphin.home.di.homeModule


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()

            modules(
                coreModule,
                homeModule
            )
        }
    }

}