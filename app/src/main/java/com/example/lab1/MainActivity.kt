package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import screens.HomeScreen
import screens.ProfileScreen
import com.example.lab1.viewmodels.HomeViewModel
import com.example.lab1.viewmodels.ProfileViewModel

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

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Головна") },
                    label = { Text("Головна") },
                    selected = currentScreen == AppDestinations.HOME,
                    onClick = { currentScreen = AppDestinations.HOME }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Person, contentDescription = "Профіль") },
                    label = { Text("Профіль") },
                    selected = currentScreen == AppDestinations.PROFILE,
                    onClick = { currentScreen = AppDestinations.PROFILE }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                AppDestinations.HOME -> HomeScreen()
                AppDestinations.PROFILE -> ProfileScreen()
            }
        }
    }
}

@Composable
fun SubScreen1(
    onNavigateNext: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = viewModel.textValue, fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        Button(onClick = { viewModel.updateText() }) {
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