package com.example.belajar_matematika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.belajar_matematika.ui.theme.BelajarMatematikaTheme
import org.opencv.android.OpenCVLoader


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!OpenCVLoader.initDebug()) {
            return
        }


        setContent {
            BelajarMatematikaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val navController = rememberNavController()
                    SetUpNavGraph(navController = navController)

                }
            }
        }
    }
}



