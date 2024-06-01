package com.example.belajar_matematika.role_guru

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.belajar_matematika.Screen
import com.example.belajar_matematika.ui.theme.GuruColor
import com.example.belajar_matematika.ui.theme.SecondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TokenScreen(
    navController: NavController
) {
    var tokenSoal by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Token",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 50.dp)
        )
        TextField(
            value = tokenSoal,
            onValueChange = { tokenSoal = it},
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = SecondaryColor,
                focusedBorderColor = SecondaryColor,
                containerColor = Color.Transparent,
                textColor = Color.Black
            ),
            modifier = Modifier
                .padding(start = 50.dp, top = 2.dp)
                .width(311.dp),

            )

        if (tokenSoal.isEmpty()) {
            Text(
                text = errorText,
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(start = 50.dp, top = 2.dp)
                    .align(Alignment.Start)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 13.dp, end = 20.dp)
        ) {

            Button(
                onClick = {
                    if (tokenSoal.isEmpty()) {
                        errorText = "Token harus diisi!"
                    }
                    else{
                        navController.navigate(Screen.Guru.createRouteToken(tokenSoal))
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(81.dp)
                    .height(34.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GuruColor,
                    contentColor = Color.White
                )

            ) {
                Text(text = "Next")
            }
        }
    }
}