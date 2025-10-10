package jva.cloud.democomposemultiplatform

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import jva.cloud.democomposemultiplatform.presentation.components.SetupImageLoader
import jva.cloud.democomposemultiplatform.presentation.views.nav.Navigation

@Composable
fun App() {
    SetupImageLoader()
    val isDarkTheme = isSystemInDarkTheme()
    val colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Surface(modifier = Modifier.fillMaxSize()) {
            val navController: NavHostController = rememberNavController()
            Navigation(navController = navController)
        }

    }
}