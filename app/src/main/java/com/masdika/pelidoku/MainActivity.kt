package com.masdika.pelidoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.masdika.pelidoku.ui.screen.HomeScreen
import com.masdika.pelidoku.ui.theme.PelidokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PelidokuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

//@Composable
//fun HomeScreen() {
//    Column(
//        modifier = Modifier.padding(16.dp).
//        fillMaxSize(),
//        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
//        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
//    ) {
//        Text(
//            text = "Wellcome Pelidoku!",
//            style = MaterialTheme.typography.displayMedium
//        )
//        Text(
//            text = "Smart AI Sudoku Solver",
//            style = MaterialTheme.typography.bodyLarge
//        )
//    }
//}