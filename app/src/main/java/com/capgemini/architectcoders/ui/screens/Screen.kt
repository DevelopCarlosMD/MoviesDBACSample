package com.capgemini.architectcoders.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.capgemini.architectcoders.ui.theme.ArchitectCodersTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    ArchitectCodersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}