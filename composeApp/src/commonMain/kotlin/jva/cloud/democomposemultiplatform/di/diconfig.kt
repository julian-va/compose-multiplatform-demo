package jva.cloud.democomposemultiplatform.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import jva.cloud.democomposemultiplatform.data.network.repository.ProductNetworkRepositoryImpl
import jva.cloud.democomposemultiplatform.data.network.repository.UserNetworkRepositoryImpl
import jva.cloud.democomposemultiplatform.domain.repository.ProductNetworkRepository
import jva.cloud.democomposemultiplatform.domain.repository.UserNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.CreateUser
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverAllProductFromRemote
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverProductFromRemote
import jva.cloud.democomposemultiplatform.domain.usecase.SignInUser
import jva.cloud.democomposemultiplatform.domain.usecase.impl.CreateUserImpl
import jva.cloud.democomposemultiplatform.domain.usecase.impl.RetrieverAllProductFromRemoteImpl
import jva.cloud.democomposemultiplatform.domain.usecase.impl.RetrieverProductFromRemoteImpl
import jva.cloud.democomposemultiplatform.domain.usecase.impl.SignInUserImpl
import jva.cloud.democomposemultiplatform.presentation.viewmodel.createaccount.CreateAccountViewModel
import jva.cloud.democomposemultiplatform.presentation.viewmodel.home.HomeVieMode
import jva.cloud.democomposemultiplatform.presentation.viewmodel.homedetail.HomeDetailVieModel
import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.LoginViewModel
import jva.cloud.democomposemultiplatform.utils.ConstantApp.BASE_URL_HOST_FAKE_API
import jva.cloud.democomposemultiplatform.utils.ConstantApp.CONNECT_TIMEOUT_MILLIS
import jva.cloud.democomposemultiplatform.utils.ConstantApp.KTOR_LOGGER
import jva.cloud.democomposemultiplatform.utils.ConstantApp.QUALIFIER_FAKE_API_CLIENT
import jva.cloud.democomposemultiplatform.utils.ConstantApp.REQUEST_TIMEOUT_MILLIS
import jva.cloud.democomposemultiplatform.utils.ConstantApp.SOCKET_TIMEOUT_MILLIS
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val clientHttpModule = module {
    single<HttpClient>(named(QUALIFIER_FAKE_API_CLIENT)) {
        createHttpClientFakeApi(get())
    }
}

val repositoryModule = module {
    single<ProductNetworkRepository> {
        ProductNetworkRepositoryImpl(get(qualifier = named(QUALIFIER_FAKE_API_CLIENT)))
    }
    single<UserNetworkRepository> {
        UserNetworkRepositoryImpl(
            get(
                qualifier = named(
                    QUALIFIER_FAKE_API_CLIENT
                )
            )
        )
    }
}

val useCaseModule = module {
    factory<RetrieverAllProductFromRemote> { RetrieverAllProductFromRemoteImpl(get()) }
    factory<RetrieverProductFromRemote> { RetrieverProductFromRemoteImpl(get()) }
    factory<SignInUser> { SignInUserImpl(get()) }
    factory<CreateUser> { CreateUserImpl(get()) }

}
val viewModelModulo = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeVieMode)
    viewModelOf(::HomeDetailVieModel)
    viewModelOf(::CreateAccountViewModel)
}

expect val platformModule: Module
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            platformModule,
            clientHttpModule,
            repositoryModule,
            useCaseModule,
            viewModelModulo
        )
    }
}

private fun createHttpClientFakeApi(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("$KTOR_LOGGER: $message")
                }
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
            socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL_HOST_FAKE_API
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
    }
}