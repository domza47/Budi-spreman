package com.example.budispreman

val scenarioSteps = listOf(
    ScenarioStep(
        text = "Potres! Tlo se trese, što prvo radiš?",
        options = listOf(
            ScenarioOption("Sakrijem se ispod stola", true, "Bravo! Pravilna reakcija."),
            ScenarioOption("Trčim van iz zgrade", false, "Nije preporučeno – možeš se ozlijediti od krhotina."),
            ScenarioOption("Stanem pod dovratak", false, "Dovratnici više nisu preporuka u modernim zgradama.")
        )
    ),
    ScenarioStep(
        text = "Nakon prestanka potresa, što je sljedeće?",
        options = listOf(
            ScenarioOption("Provjerim ima li ozlijeđenih", true, "Ispravno – prvo pomažeš drugima."),
            ScenarioOption("Odmah uključim televizor", false, "Ne – može doći do požara zbog oštećenja struje."),
            ScenarioOption("Čistim stan", false, "Ne – prioritet je sigurnost.")
        )
    )
)
