package pet.dolphin.home.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import pet.dolphin.home.data.remote.HomeRemoteSource
import pet.dolphin.home.data.repository.HomeRepositoryImpl
import pet.dolphin.home.domain.repository.HomeRepository
import pet.dolphin.home.domain.usecase.GetTopPopularFundsUseCase
import pet.dolphin.home.presentation.HomeViewModel

val homeModule = module {
    includes(
        domain,
        data,
        presentation
    )
}

private val data = module {
    singleOf(::HomeRemoteSource).bind<HomeRemoteSource>()
    singleOf(::HomeRepositoryImpl).bind<HomeRepository>()
}

private val presentation = module {
    viewModelOf(::HomeViewModel)
}

private val domain = module {
    factoryOf(::GetTopPopularFundsUseCase)
}