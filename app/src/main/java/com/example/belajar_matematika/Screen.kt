package com.example.belajar_matematika

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Siswa: Screen(route = "siswa_screen")
    object Canvas: Screen(route = "canvas_screen/{identitas}/{token}") {
        fun createRoute(identitas: String, token: String) = "canvas_screen/$identitas/$token"
    }
    object Token: Screen(route = "token_screen")
    object Guru: Screen(route = "guru_screen/{token}") {
        fun createRouteToken(token: String) = "guru_screen/$token"
    }
    object Leaderboard: Screen(route = "leadrboard_screen")
}
