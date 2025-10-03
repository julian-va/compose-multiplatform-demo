package jva.cloud.democomposemultiplatform.di

import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.LoginViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val viewModelModulo = module {
    viewModelOf(::LoginViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(viewModelModulo)
    }
}