package com.example.belajar_matematika

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaScreen(
    navController: NavController
) {
    var identitasSiswa by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Identitas Siswa",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 50.dp)
        )
        TextField(
            value = identitasSiswa,
            onValueChange = { identitasSiswa = it},
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = colorResource(id = R.color.primary),
                focusedBorderColor = colorResource(id = R.color.primary),
                containerColor = Color.Transparent,
                textColor = Color.Black
            ),
//            isError = identitasSiswa.isEmpty(),
            modifier = Modifier
                .padding(start = 50.dp, top = 2.dp)
                .width(311.dp),

        )

        if (identitasSiswa.isEmpty()) {
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
                    if (identitasSiswa.isEmpty()) {
                        errorText = "Identitas harus diisi!"
                    }
                    else{
                        navController.navigate(route = Screen.Canvas.route)
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(81.dp)
                    .height(34.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primary),
                    contentColor = Color.White
                )

            ) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SiswaScreenPrev() {
    SiswaScreen(navController = rememberNavController())
}