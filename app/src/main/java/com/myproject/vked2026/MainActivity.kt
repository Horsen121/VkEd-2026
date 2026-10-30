package com.myproject.vked2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myproject.vked2026.presentation.screens.AppDetailsRoute
import com.myproject.vked2026.presentation.screens.AppDetailsScreenRoute
import com.myproject.vked2026.presentation.screens.AppListRoute
import com.myproject.vked2026.presentation.screens.AppListScreenRoute
import com.myproject.vked2026.ui.theme.VkEd2026Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEd2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = AppListRoute.ROUTE
                    ) {
                        composable(route = AppListRoute.ROUTE) {
                            AppListScreenRoute { appId ->
                                navController.navigate(AppDetailsRoute.ROUTE + "/$appId")
                            }
                        }

                        composable(
                            route = AppDetailsRoute.ROUTE + "/{appId}",
                            arguments = listOf(navArgument("appId") { type = NavType.StringType })
                        ) { navBackStack ->
                            val appId = navBackStack.arguments?.getString("appId") ?: ""
                            AppDetailsScreenRoute(appId) {
                                navController.navigate(AppListRoute.ROUTE)
                            }
                        }
                    }
                }
            }
        }
    }
}
