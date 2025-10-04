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
import jva.cloud.democomposemultiplatform.domain.repository.ProductNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverAllProductFromRemote
import jva.cloud.democomposemultiplatform.domain.usecase.impl.RetrieverAllProductFromRemoteImpl
import jva.cloud.democomposemultiplatform.presentation.viewmodel.login.LoginViewModel
import jva.cloud.democomposemultiplatform.presentation.views.TestComposeKtorVieModel
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val clientHttpModule = module {
    single<HttpClient>(named("FakeApiClient")) {
        createHttpClientFakeApi(get())
    }
}

val repositoryModule = module {
    single<ProductNetworkRepository> {
        ProductNetworkRepositoryImpl(get(qualifier = named("FakeApiClient")))
    }
}

val useCaseModule = module {
    factory<RetrieverAllProductFromRemote> { RetrieverAllProductFromRemoteImpl(get()) }
}
val viewModelModulo = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::TestComposeKtorVieModel)
}

expect val platformModule: Module
fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(platformModule, clientHttpModule, repositoryModule, useCaseModule, viewModelModulo)
    }
}

private fun createHttpClientFakeApi(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("Ktor Logger: $message")
                }
            }
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 2000L
            connectTimeoutMillis = 2000L
            socketTimeoutMillis = 2000L
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            url {
                protocol = URLProtocol.HTTPS
                host = "api.escuelajs.co/api/v1"
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
