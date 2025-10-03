package jva.cloud.democomposemultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import jva.cloud.democomposemultiplatform.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }