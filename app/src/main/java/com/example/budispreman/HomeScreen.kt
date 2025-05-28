package com.example.budispreman

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budispreman.ui.theme.BudiSpremanTheme

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Budi Spreman!",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(onClick = { navController.navigate("simulation") }) {
            Text("Simulacija katastrofe")
        }
        Button(onClick = { navController.navigate("quiz") }) {
            Text("Kviz: Koliko si spreman?")
        }
        Button(onClick = { navController.navigate("backpack") }) {
            Text("Spremi ruksak")
        }
        Button(onClick = { navController.navigate("info") }) {
            Text("Info i volontiranje")
        }
    }
}
