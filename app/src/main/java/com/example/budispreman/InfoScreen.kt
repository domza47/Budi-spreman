package com.example.budispreman

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.budispreman.EmergencyItem

@Composable
fun InfoScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Priprema za katastrofe", style = MaterialTheme.typography.titleLarge)

        Text(
            "- Pripremite kućni plan evakuacije\n" +
                    "- Osigurajte zalihu vode, hrane i prve pomoći\n" +
                    "- Naučite osnovnu prvu pomoć\n",
            style = MaterialTheme.typography.bodyMedium
        )

        Text("Volontiraj s Crvenim križem", style = MaterialTheme.typography.titleMedium)

        Text(
            "Hrvatski Crveni križ uvijek traži volontere za pomoć u kriznim situacijama.\nProvjeri sve informacije na webu:",
            style = MaterialTheme.typography.bodyMedium
        )

        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hck.hr/kako-pomoci/volontirajte/29"))
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Posjeti web stranicu")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Dodaj sliku loga na dno
        androidx.compose.foundation.Image(
            painter = painterResource(id = R.drawable.hck_logo), // sliku nazovi 'hck_logo.jpg'
            contentDescription = "Logo Hrvatskog Crvenog križa",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(top = 16.dp)
        )
    }
}

