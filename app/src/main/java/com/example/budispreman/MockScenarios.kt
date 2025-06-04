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
    ),
    ScenarioStep(
        text = "U tvojoj zgradi je izbio požar! Što radiš?",
        options = listOf(
            ScenarioOption("Spuštam se stepenicama i pokrivam usta krpom", true, "Točno! Zaštita od dima i siguran izlaz."),
            ScenarioOption("Koristim lift za brži izlazak", false, "Ne – liftovi nisu sigurni tijekom požara."),
            ScenarioOption("Ostajem unutra i čekam vatrogasce", false, "Pogrešno – moraš pokušati napustiti zgradu na siguran način.")
        )
    ),
    ScenarioStep(
        text = "Poplava prijeti tvom naselju. Što je ispravna priprema?",
        options = listOf(
            ScenarioOption("Spremam osnovne potrepštine i selim se na više katove", true, "Odlično – priprema i visina su ključni."),
            ScenarioOption("Odlazim do podruma jer je tamo hladno", false, "Ne – podrum je opasan kod poplave."),
            ScenarioOption("Ostavljam prozore otvorene za ventilaciju", false, "Ne – voda može još lakše ući.")
        )
    ),
    ScenarioStep(
        text = "Zatekao te uragan. Gdje se skloniš?",
        options = listOf(
            ScenarioOption("U unutarnju prostoriju bez prozora", true, "Točno – što dalje od stakla i vanjskih zidova."),
            ScenarioOption("U garažu", false, "Ne – garaže često nemaju jaku strukturu."),
            ScenarioOption("Uz prozor, da vidim što se događa", false, "Opasno – staklo može puknuti.")
        )
    ),
    ScenarioStep(
        text = "Utvrdili su da je došlo do kemijskog curenja u blizini. Što radiš?",
        options = listOf(
            ScenarioOption("Ostajem unutra i zatvaram sve otvore", true, "Ispravno – zatvaranje smanjuje izloženost."),
            ScenarioOption("Izlazim van da bolje vidim što se događa", false, "Ne – to te može izložiti opasnim tvarima."),
            ScenarioOption("Pijem puno vode da 'isperem' zrak", false, "Nažalost, to ne pomaže u ovoj situaciji.")
        )
    )
)
