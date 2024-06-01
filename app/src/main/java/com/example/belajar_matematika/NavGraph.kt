package com.example.belajar_matematika

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.belajar_matematika.role_guru.CalculatorViewModel
import com.example.belajar_matematika.role_guru.LeaderboardScreen
import com.example.belajar_matematika.role_guru.LeaderboardViewModel
import com.example.belajar_matematika.role_guru.RoleGuruScreen
import com.example.belajar_matematika.role_guru.TokenScreen

@Composable
fun SetUpNavGraph(
    navController:NavHostController,
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
            val tokenViewModel = viewModel<TokenViewModel>()
           SiswaScreen(viewModel = tokenViewModel, navController = navController )
        }
        composable(
            route = Screen.Canvas.route,
            arguments = listOf(
                navArgument("identitas") {
                    type = NavType.StringType
                },
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val identitas = it.arguments?.getString("identitas") ?: ""
            val token = it.arguments?.getString("token") ?: ""
            CanvasSiswa(identitas = identitas, token = token, navController)
        }
        composable(
            route = Screen.Token.route
        ) {
           TokenScreen(navController)
        }
        composable(
            route = Screen.Guru.route,
            arguments = listOf(
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) {
            val token = it.arguments?.getString("token") ?: ""
            val calculatorViewModel = viewModel<CalculatorViewModel>()
            RoleGuruScreen(modifier = Modifier, calculatorViewModel = calculatorViewModel, navController, token)
        }
        composable(
            route = Screen.Leaderboard.route
        ) {
            val leaderboardViewModel = viewModel<LeaderboardViewModel>()
            LeaderboardScreen(leaderboardViewModel)
        }
    }
}