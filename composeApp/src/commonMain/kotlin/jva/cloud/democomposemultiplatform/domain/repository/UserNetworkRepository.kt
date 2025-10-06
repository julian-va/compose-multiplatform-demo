package jva.cloud.democomposemultiplatform.domain.repository

import jva.cloud.democomposemultiplatform.domain.model.CreatedUser
import jva.cloud.democomposemultiplatform.domain.model.Credentials
import jva.cloud.democomposemultiplatform.domain.model.Token
import jva.cloud.democomposemultiplatform.domain.model.User

interface UserNetworkRepository {
    suspend fun login(credentials: Credentials): Result<Token>
    suspend fun createUser(user: User): Result<CreatedUser>
}