package jva.cloud.democomposemultiplatform.presentation.viewmodel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.domain.usecase.LogOutUser
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverAllProductFromRemote
import kotlinx.coroutines.launch

class HomeVieMode(
    private val retrieverProducts: RetrieverAllProductFromRemote,
    private val logOutUser: LogOutUser
) : ViewModel() {
    var state by mutableStateOf(HomeVieModelState())
        private set

    init {
        retrieverAllProduct()
    }

    fun retrieverAllProduct() {
        viewModelScope.launch {
            val result: Result<List<Product>> = retrieverProducts.retrieveAllProducts()
            result.onSuccess { products ->
                state = state.copy(isLoading = false, products = products)
            }
            result.onFailure {
                state = state.copy(isLoading = false, products = emptyList(), error = true)
            }
        }
    }

    fun logOut(goToLogin: () -> Unit) {
        viewModelScope.launch {
            logOutUser.logOutUser()
            goToLogin()
        }
    }

}