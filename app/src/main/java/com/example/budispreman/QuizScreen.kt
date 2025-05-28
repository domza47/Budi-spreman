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

@Composable
fun QuizScreen() {
    var questionIndex by remember { mutableStateOf(0) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }
    var isFinished by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(10) }
    var timeExpired by remember { mutableStateOf(false) }

    val currentQuestion = quizQuestions[questionIndex]

    // Timer efekt
    LaunchedEffect(questionIndex, selectedIndex) {
        timeLeft = 10
        timeExpired = false
        while (timeLeft > 0 && selectedIndex == null) {
            delay(1000)
            timeLeft--
        }
        if (timeLeft == 0 && selectedIndex == null) {
            timeExpired = true
        }
    }

    if (isFinished) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Kviz završen!", style = MaterialTheme.typography.headlineSmall)
            Text("Točno: $correctAnswers / ${quizQuestions.size}", color = Color.Blue)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                questionIndex = 0
                selectedIndex = null
                correctAnswers = 0
                isFinished = false
            }) {
                Text("Pokušaj ponovno")
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(currentQuestion.question, style = MaterialTheme.typography.titleLarge)

            Text("Preostalo vrijeme: $timeLeft sek", color = Color.Gray)

            currentQuestion.options.forEachIndexed { index, answer ->
                Button(
                    onClick = {
                        selectedIndex = index
                        if (index == currentQuestion.correctAnswerIndex) correctAnswers++
                    },
                    enabled = selectedIndex == null && !timeExpired,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(answer)
                }
            }

            selectedIndex?.let {
                val correct = it == currentQuestion.correctAnswerIndex
                Text(
                    text = if (correct) "Točno!" else "Netočno",
                    color = if (correct) Color.Green else Color.Red
                )
            }

            if (timeExpired && selectedIndex == null) {
                Text("Vrijeme isteklo!", color = Color.Red)
            }

            if (selectedIndex != null || timeExpired) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    if (questionIndex < quizQuestions.lastIndex) {
                        questionIndex++
                        selectedIndex = null
                        timeLeft = 10
                        timeExpired = false
                    } else {
                        isFinished = true
                    }
                }) {
                    Text(if (questionIndex < quizQuestions.lastIndex) "Dalje" else "Završi")
                }
            }
        }
    }
}
