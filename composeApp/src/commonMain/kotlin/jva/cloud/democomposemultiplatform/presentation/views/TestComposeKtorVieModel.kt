package jva.cloud.democomposemultiplatform.presentation.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jva.cloud.democomposemultiplatform.domain.model.Product
import jva.cloud.democomposemultiplatform.domain.usecase.RetrieverAllProductFromRemote
import kotlinx.coroutines.launch

class TestComposeKtorVieModel(private val usacase: RetrieverAllProductFromRemote) : ViewModel() {
    var state by mutableStateOf<List<Product>>(emptyList())
        private set

    init {
        retriverAllProduct()
    }

    fun retriverAllProduct() {
        viewModelScope.launch {
            state = usacase.retrieveAllProducts()
        }
    }
}