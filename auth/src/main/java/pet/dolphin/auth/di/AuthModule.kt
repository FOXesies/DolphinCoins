package pet.dolphin.auth.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pet.dolphin.auth.presentation.AuthViewModel

val authModule = module {
    viewModelOf(::AuthViewModel)
}