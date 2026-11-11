package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

enum class AppDestinations {
    HOME, PROFILE
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    var currentScreen by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { currentScreen = AppDestinations.HOME }) { Text("Головна") }
            Button(onClick = { currentScreen = AppDestinations.PROFILE }) { Text("Профіль") }
        }

        when (currentScreen) {
            AppDestinations.HOME -> HomeScreen()
            AppDestinations.PROFILE -> ProfileScreen()
        }
    }
}

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "sub_screen_1") {

        composable("sub_screen_1") {
            SubScreen1(
                onNavigateNext = { navController.navigate("sub_screen_2") }
            )
        }

        composable("sub_screen_2") {
            SubScreen2(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun SubScreen1(onNavigateNext: () -> Unit) {
    var textValue by remember { mutableStateOf("Початковий текст") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = textValue, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = { textValue = "Текст змінено" }) {
            Text("Змінити текст")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onNavigateNext) {
            Text("Перейти на другий підекран")
        }
    }
}

@Composable
fun SubScreen2(onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Це другий підекран", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))

        Button(onClick = onNavigateBack) {
            Text("Повернутися назад")
        }
    }
}

@Composable
fun ProfileScreen() {
    var textValue by rememberSaveable { mutableStateOf("Початковий текст Профілю") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = textValue, fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        Button(onClick = { textValue = "Текст змінено через rememberSaveable!" }) {
            Text("Оновити дані")
        }
    }
}