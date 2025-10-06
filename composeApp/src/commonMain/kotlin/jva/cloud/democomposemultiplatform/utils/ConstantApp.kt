package jva.cloud.democomposemultiplatform.utils

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
}
