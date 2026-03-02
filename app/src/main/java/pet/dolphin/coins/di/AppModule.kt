package pet.dolphin.coins.di

import org.koin.dsl.module
import pet.dolphin.core.di.coreModule
import pet.dolphin.home.di.homeModule

val appModule = module{
    includes(
        coreModule,
        homeModule
    )
}