package com.example.budispreman

import android.media.MediaPlayer
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.budispreman.ui.theme.BudiSpremanTheme

@Composable
fun BackpackScreen() {
    val context = LocalContext.current
    val yayPlayer = remember { MediaPlayer.create(context, R.raw.yay) }
    val nayPlayer = remember { MediaPlayer.create(context, R.raw.nay) }

    var droppedItems by remember { mutableStateOf(setOf<String>()) }
    var availableItems by remember { mutableStateOf(emergencyItems.map { it.name }) }
    var showResult by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(60) }
    var timerFinished by remember { mutableStateOf(false) }

    val correctItems = emergencyItems.filter { it.isEssential }.map { it.name }.toSet()
    val animatingItems = remember { mutableStateMapOf<String, Boolean>() }
    val coroutineScope = rememberCoroutineScope()

    // Timer
    LaunchedEffect(timeLeft, timerFinished) {
        if (!timerFinished && timeLeft > 0) {
            delay(1000)
            timeLeft--
        } else if (timeLeft == 0 && !timerFinished) {
            timerFinished = true
            showResult = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Preostalo vrijeme: $timeLeft s",
            style = MaterialTheme.typography.titleMedium,
            color = if (timeLeft <= 10) Color.Red else Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text("Klikni na predmete koje želiš staviti u ruksak:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.backpack),
                contentDescription = "Ruksak",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(availableItems) { itemName ->
                var isAnimating by remember { mutableStateOf(false) }
                val offsetY by animateDpAsState(if (isAnimating) (-1000).dp else 0.dp)

                Box(
                    modifier = Modifier
                        .offset(y = offsetY)
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .clickable(enabled = !isAnimating) {
                            isAnimating = true
                            animatingItems[itemName] = true
                            coroutineScope.launch {
                                delay(500)
                                if (itemName in correctItems) {
                                    yayPlayer.start()
                                    droppedItems = droppedItems + itemName
                                    availableItems = availableItems - itemName
                                } else {
                                    nayPlayer.start()
                                }
                                isAnimating = false
                                animatingItems.remove(itemName)
                            }
                        }
                        .padding(12.dp)
                ) {
                    Text(itemName)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (showResult) {
            val correctDropped = droppedItems.count { it in correctItems }
            Text(
                "Točno si ubacio $correctDropped od ${correctItems.size} važnih predmeta.",
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    droppedItems = emptySet()
                    availableItems = emergencyItems.map { it.name }
                    showResult = false
                    timerFinished = false
                    timeLeft = 60
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pokušaj ponovno")
            }
        } else {
            Button(
                onClick = {
                    showResult = true
                    timerFinished = true
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !timerFinished
            ) {
                Text("Provjeri rezultat")
            }
        }
    }
}
