package jva.cloud.democomposemultiplatform.data.network.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import jva.cloud.democomposemultiplatform.data.network.entity.TokenNetworkEntity
import jva.cloud.democomposemultiplatform.data.network.mapper.toDomain
import jva.cloud.democomposemultiplatform.data.network.mapper.toNetwork
import jva.cloud.democomposemultiplatform.domain.model.Credentials
import jva.cloud.democomposemultiplatform.domain.model.Token
import jva.cloud.democomposemultiplatform.domain.repository.UserNetworkRepository
import jva.cloud.democomposemultiplatform.utils.ConstantApp.ENDPOINT_LOGIN

class UserNetworkRepositoryImpl(private val client: HttpClient) : UserNetworkRepository {
    companion object {
        private const val LOGIN_ERROR_MESSAGE = "Error: "
    }

    override suspend fun login(credentials: Credentials): Result<Token> {
        return try {
            val response: HttpResponse = client.post(urlString = ENDPOINT_LOGIN) {
                setBody(credentials.toNetwork())
            }
            if (response.status.isSuccess()) {
                val tokenNetworkEntity: TokenNetworkEntity = response.body()
                Result.success(tokenNetworkEntity.toDomain())
            } else {
                return Result.failure(Exception("$LOGIN_ERROR_MESSAGE${response.status.value}"))
            }
        } catch (e: Exception) {
            Result.failure(exception = e)

        }
    }
}
