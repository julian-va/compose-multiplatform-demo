package jva.cloud.democomposemultiplatform.presentation.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object TestCompose

@Composable
fun TestComposeKtor(vm: TestComposeKtorVieModel = koinViewModel()): Unit {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(vm.state.size) { index ->
            Text(text = vm.state[index].title)
        }
    }
}