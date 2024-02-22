package com.example.belajar_matematika

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.belajar_matematika.role_guru.Calculator
import com.example.belajar_matematika.role_guru.CalculatorViewModel

@Composable
fun SetUpNavGraph(
    navController:NavHostController
) {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state
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
            route = Screen.Canvas.route
        ) {
            CanvasSiswa()
        }
        composable(
            route = Screen.Guru.route
        ) {
            Calculator(state = state, onAction = viewModel::onAction)
        }
    }
}