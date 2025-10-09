package jva.cloud.democomposemultiplatform.utils

import org.koin.core.qualifier.named

object ConstantApp {
    //koin
    const val QUALIFIER_FAKE_API_CLIENT = "FakeApiClient"

    //ktor-fake-api-config
    const val BASE_URL_HOST_FAKE_API = "api.escuelajs.co"
    const val REQUEST_TIMEOUT_MILLIS = 2000L
    const val CONNECT_TIMEOUT_MILLIS = 2000L
    const val SOCKET_TIMEOUT_MILLIS = 2000L
    const val KTOR_LOGGER = "Ktor Logger"

    //ktor-product
    const val ENDPOINT_PRODUCTS = "/api/v1/products"

    //ktor-auth
    const val ENDPOINT_LOGIN = "/api/v1/auth/login"

    //ktor-user
    const val ENDPOINT_USERS = "/api/v1/users"

    //App
    const val ONE = 1
    const val STRING_EMPTY: String = ""
    const val DEFAULT_AVATAR_URL = "https://i.imgur.com/yhW6Yw1.jpg"

    //DataStore
    val QUALIFIER_DATASTORE_PATH = named("data_store_path")
    internal const val DATA_STORE_FILE_NAME = "dice.preferences_pb"
    const val TOKEN_KEY = "token"
    const val CURRENT_USER_KEY = "current_user_created"
    const val CURRENT_PASSWORD_KEY = "current_password_created"
    const val DEFAULT_USER_EMAIL = "john@mail.com"
    const val DEFAULT_USER_PASSWORD = "changeme"

    // Remember Session
    const val REMEMBER_SESSION_KEY = "remember"
    const val REMEMBER_SESSION_TRUE = "true"
    const val REMEMBER_SESSION_FALSE = "false"
}
