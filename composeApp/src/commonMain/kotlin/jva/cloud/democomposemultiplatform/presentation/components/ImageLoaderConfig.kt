package jva.cloud.democomposemultiplatform.presentation.components

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger

@Composable
fun SetupImageLoader() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader
            .Builder(context)
            .crossfade(true)
            .logger(DebugLogger()).build()
    }
}