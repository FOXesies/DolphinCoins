package pet.dolphin.coins

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import pet.dolphin.auth.di.authModule
import pet.dolphin.core.di.coreModule
import pet.dolphin.home.di.homeModule


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@App)
            androidLogger()

            modules(
                coreModule,
                homeModule,
                authModule
            )
        }
    }

}