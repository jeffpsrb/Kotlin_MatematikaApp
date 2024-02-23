package com.example.belajar_matematika

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.belajar_matematika.role_guru.CalculatorScreen
import com.example.belajar_matematika.role_guru.CalculatorViewModel

@Composable
fun SetUpNavGraph(
    navController:NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Siswa.route
        ) {
            SiswaScreen(navController = navController)
        }
        composable(
            route = Screen.Canvas.route + "/{result}",
            arguments = listOf(
                navArgument("result") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val result = navBackStackEntry.arguments?.getString("result") ?: ""
            CanvasSiswa(result = result)
        }
        composable(
            route = Screen.Guru.route
        ) {
            val viewModel = viewModel<CalculatorViewModel>()
            CalculatorScreen(viewModel = viewModel, navController = navController)
        }
    }
}