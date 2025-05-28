package com.example.budispreman

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budispreman.ui.theme.BudiSpremanTheme
import kotlinx.coroutines.delay

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.budispreman.EmergencyItem


@Composable
fun BackpackScreen() {
    var selectedItems by remember { mutableStateOf(setOf<String>()) }
    var showResult by remember { mutableStateOf(false) }

    val correctCount = emergencyItems.count { it.isEssential }
    val selectedCorrect = emergencyItems.count { it.isEssential && selectedItems.contains(it.name) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Odaberi predmete koje bi stavio u torbu za katastrofu:",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(emergencyItems) {
                emergencyItem ->
                val isSelected = selectedItems.contains(emergencyItem.name)
                val backgroundColor = if (isSelected) Color(0xFFE0F7FA) else Color.White

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .clickable {
                            selectedItems = if (isSelected) {
                                selectedItems - emergencyItem.name
                            } else {
                                selectedItems + emergencyItem.name
                            }
                        }
                        .padding(12.dp)
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                ) {
                    Text(emergencyItem.name)
                }
            }
        }

        Button(
            onClick = { showResult = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Provjeri rezultat")
        }

        if (showResult) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Točno si odabrao $selectedCorrect od $correctCount ključnih predmeta.",
                color = Color.Blue
            )

            Button(
                onClick = {
                    selectedItems = emptySet()
                    showResult = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pokušaj ponovno")
            }
        }
    }
}
