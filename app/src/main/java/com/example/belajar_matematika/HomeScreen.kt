package com.example.belajar_matematika

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Row (
            modifier = Modifier
                .align(Alignment.Start),
            verticalAlignment = Alignment.Top

        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangle_1),
                contentDescription = "rectangle 1",
                modifier = Modifier

                    .width(196.dp)
                    .height(234.dp)

            )
        }
        Spacer(modifier = Modifier.height(80.dp))

        Column (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                       navController.navigate(route = Screen.Siswa.route)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(247.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary),
                    contentColor = Color.White
                )

            ) {
                Text(
                    text = "Role Siswa",
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = {
                          navController.navigate(route = Screen.Guru.route)
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(247.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.secondary),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Role Guru",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(80.dp))

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.End),
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.rectangle_2),
                contentDescription = "rectangle 2",
                modifier = Modifier
                    .width(196.dp)
                    .height(234.dp)
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPrev() {
    HomeScreen(
        navController = rememberNavController()
    )
}