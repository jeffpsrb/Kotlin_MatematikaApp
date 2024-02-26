package com.example.belajar_matematika

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.belajar_matematika.role_guru.CalculatorViewModel
import com.example.belajar_matematika.role_guru.RoleGuruScreen

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
            route = Screen.Guru.route
        ) {
            val calculatorViewModel = viewModel<CalculatorViewModel>()
            RoleGuruScreen(modifier = Modifier, calculatorViewModel = calculatorViewModel)
        }
    }
}