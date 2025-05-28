package com.example.budispreman

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun SimulationScreen() {
    var stepIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<ScenarioOption?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }
    var isFinished by remember { mutableStateOf(false) }

    if (isFinished) {
        // Završni ekran
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Završio si simulaciju!", style = MaterialTheme.typography.headlineSmall)
            Text("Točnih odgovora: $correctAnswers / ${scenarioSteps.size}", color = Color.Blue)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                stepIndex = 0
                selectedOption = null
                correctAnswers = 0
                isFinished = false
            }) {
                Text("Pokušaj ponovno")
            }
        }
    } else {
        // Tijek simulacije
        val step = scenarioSteps[stepIndex]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(step.text, style = MaterialTheme.typography.bodyMedium)

            step.options.forEach { option ->
                Button(
                    onClick = {
                        selectedOption = option
                        if (option.isCorrect) correctAnswers++
                    },
                    enabled = selectedOption == null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(option.text)
                }
            }

            selectedOption?.let { option ->
                Text(
                    text = option.feedback,
                    color = if (option.isCorrect) Color.Green else Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (stepIndex < scenarioSteps.lastIndex) {
                            stepIndex++
                            selectedOption = null
                        } else {
                            isFinished = true
                        }
                    }
                ) {
                    Text(if (stepIndex < scenarioSteps.lastIndex) "Dalje" else "Završi")
                }
            }
        }
    }
}

