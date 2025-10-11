package jva.cloud.democomposemultiplatform.presentation.viewmodel.homedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverProductFromRemote
import kotlinx.coroutines.launch

class HomeDetailVieModel(
    private val productId: Int,
    private val retrieverProductFromRemote: RetrieverProductFromRemote
) : ViewModel() {
    var state by mutableStateOf(HomeDetailVieModelState())
        private set

    init {
        viewModelScope.launch {
            val result: Result<Product> = retrieverProductFromRemote.retrieveProductById(productId)
            result.onSuccess { product ->
                state = state.copy(product = product, isLoading = false)
            }.onFailure { error ->
                state = state.copy(isLoading = false, error = true)
            }

        }
    }
}