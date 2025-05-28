package com.example.budispreman

data class ScenarioStep(
    val text: String,
    val options: List<ScenarioOption>
)

data class ScenarioOption(
    val text: String,
    val isCorrect: Boolean,
    val feedback: String
)
