package screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab1.SubScreen1
import com.example.lab1.SubScreen2

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