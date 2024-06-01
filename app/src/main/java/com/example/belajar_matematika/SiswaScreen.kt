package com.example.belajar_matematika

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.belajar_matematika.ui.theme.GuruColor
import com.example.belajar_matematika.ui.theme.SecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaScreen(
    viewModel: TokenViewModel,
    navController: NavController,
) {
    var identitasSiswa by remember { mutableStateOf("") }
    var tokenSoalInput by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    var errorToken by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        
    viewModel.loadTokenSoal()
    }

    val token = viewModel.tokenSoal.value


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
                unfocusedBorderColor = SecondaryColor,
                focusedBorderColor = SecondaryColor,
                containerColor = Color.Transparent,
                textColor = Color.Black
            ),
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
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Token",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 50.dp)
        )
        TextField(
            value = tokenSoalInput,
            onValueChange = { tokenSoalInput  = it},
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
        if (tokenSoalInput.isEmpty()) {
            Text(
                text = errorToken,
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(start = 50.dp, top = 2.dp)
                    .align(Alignment.Start)
            )
        }
        else if (tokenSoalInput != token) {
            Text(
                text = errorToken,
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(start = 50.dp, top = 2.dp)
                    .align(Alignment.Start)
            )
        }

        else if (tokenSoalInput.isEmpty() && token.isEmpty()) {
            Text(
                text = errorToken,
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
                    else if (tokenSoalInput.isEmpty()) {
                        errorToken = "Token harus diisi!"
                    }
                    else if (tokenSoalInput != token) {
                        errorToken = "Token Tidak Valid"
                    }
                    else if (tokenSoalInput.isEmpty() && token.isEmpty()) {
                        errorText = "Identitas dan Token Harus Diisi!"
                    }
                    else{
                        navController.navigate(Screen.Canvas.createRoute(identitasSiswa, token))
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

@Preview(showBackground = true)
@Composable
fun SiswaScreenPrev() {
    val tokenViewModel = viewModel<TokenViewModel>()
    SiswaScreen(tokenViewModel, navController = rememberNavController())
}