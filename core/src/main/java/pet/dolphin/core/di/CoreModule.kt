package pet.dolphin.core.di


import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module
import pet.dolphin.core.data.networking.HttpClientFactory

val coreModule = module {
    single { HttpClientFactory.create(CIO.create()) }
}