package jva.cloud.democomposemultiplatform.domain.usecase.impl

import jva.cloud.democomposemultiplatform.domain.model.Credentials
import jva.cloud.democomposemultiplatform.domain.model.Token
import jva.cloud.democomposemultiplatform.domain.repository.DateStoreRepository
import jva.cloud.democomposemultiplatform.domain.repository.UserNetworkRepository
import jva.cloud.democomposemultiplatform.domain.usecase.SignInUser
import jva.cloud.democomposemultiplatform.utils.ConstantApp.CURRENT_PASSWORD_KEY
import jva.cloud.democomposemultiplatform.utils.ConstantApp.CURRENT_USER_KEY
import jva.cloud.democomposemultiplatform.utils.ConstantApp.DEFAULT_USER_EMAIL
import jva.cloud.democomposemultiplatform.utils.ConstantApp.DEFAULT_USER_PASSWORD
import jva.cloud.democomposemultiplatform.utils.ConstantApp.TOKEN_KEY

class SignInUserImpl(
    private val userNetworkRepository: UserNetworkRepository,
    private val dateStoreRepository: DateStoreRepository
) : SignInUser {
    override suspend fun invoke(credentials: Credentials): Result<Token> {
        dateStoreRepository.deleted(key = TOKEN_KEY)
        val result = userNetworkRepository.login(credentials = checkCredentials(credentials))
        result.onSuccess {
            dateStoreRepository.save(TOKEN_KEY, it.accessToken)
        }
        return result
    }

    private suspend fun checkCredentials(credentials: Credentials): Credentials {
        val userCreate: String? = dateStoreRepository.read(key = CURRENT_USER_KEY)
        val passwordCreate: String? = dateStoreRepository.read(key = CURRENT_PASSWORD_KEY)
        if (validateFields(field1 = userCreate, field2 = credentials.email)
            && validateFields(field1 = passwordCreate, field2 = credentials.password)
        ) {
            return Credentials(email = DEFAULT_USER_EMAIL, password = DEFAULT_USER_PASSWORD)
        }
        return credentials
    }

    private fun validateFields(field1: String?, field2: String): Boolean {
        return field1 == field2
    }
}
