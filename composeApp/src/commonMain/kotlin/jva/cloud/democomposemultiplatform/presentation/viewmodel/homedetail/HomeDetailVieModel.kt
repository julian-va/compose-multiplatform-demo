package jva.cloud.democomposemultiplatform.presentation.viewmodel.homedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeDetailVieModel(private val productId: Int) : ViewModel() {
    var state by mutableStateOf(HomeDetailVieModelState())
        private set
}