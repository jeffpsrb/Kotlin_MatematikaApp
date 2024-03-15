package com.example.belajar_matematika

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Siswa: Screen(route = "siswa_screen")
    object Canvas: Screen(route = "canvas_screen/{identitas}") {
        fun createRoute(identitas: String) = "canvas_screen/$identitas"
    }
    object Guru: Screen(route = "guru_screen")
    object Leaderboard: Screen(route = "leadrboard_screen")
}
