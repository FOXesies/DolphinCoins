package pet.dolphin.auth.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pet.dolphin.auth.data.networking.AuthRemoteSource
import pet.dolphin.auth.data.repository.AuthRepositoryImpl
import pet.dolphin.auth.domain.repository.AuthRepository
import pet.dolphin.auth.domain.usecase.SignInUseCase
import pet.dolphin.auth.domain.usecase.SignUpUseCase
import pet.dolphin.auth.presentation.AuthViewModel

val authModule = module {
    factoryOf(::AuthRemoteSource)
    factoryOf(::AuthRepositoryImpl).bind<AuthRepository>()
    factoryOf(::SignInUseCase)
    factoryOf(::SignUpUseCase)
    viewModelOf(::AuthViewModel)
}