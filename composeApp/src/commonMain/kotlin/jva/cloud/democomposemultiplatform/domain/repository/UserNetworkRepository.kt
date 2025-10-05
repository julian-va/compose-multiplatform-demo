package jva.cloud.democomposemultiplatform.domain.repository

import jva.cloud.democomposemultiplatform.domain.model.Credentials
import jva.cloud.democomposemultiplatform.domain.model.Token

interface UserNetworkRepository {
    suspend fun login(credentials: Credentials): Result<Token>
}