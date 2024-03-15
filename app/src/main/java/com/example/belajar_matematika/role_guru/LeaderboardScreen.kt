package com.example.belajar_matematika.role_guru

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.belajar_matematika.ui.theme.GuruColor
import com.example.belajar_matematika.ui.theme.SiswaColor


@Composable
fun LeaderboardScreen(
    viewModel: LeaderboardViewModel
) {
    var showRefreshedNotification by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.loadIdentitasSiswa()
    }
    if (showRefreshedNotification) {
        Snackbar(
            modifier = Modifier.padding(16.dp),
            dismissAction = { showRefreshedNotification = false },
            content = { Text("Data refreshed!") }
        )
    }


    Column {
        Text(text = "Leaderboard")

        LazyColumn {
            viewModel.identitasSiswa.value.forEach { siswa ->

                item {
                    LeaderboardCard(siswa = siswa)
                }

            }
        }
        FloatingActionButton(
            onClick = {
                viewModel.refreshIdentitasSiswa()
                showRefreshedNotification = true
                      },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "Refresh",
                tint = GuruColor,

            )
        }
    }

}

@Composable
fun LeaderboardCard(siswa: LeaderboardData){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = SiswaColor,
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = siswa.identitasSiswa,
                style = TextStyle(fontSize = 18.sp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun leaderboaordScreen() {
//    val viewModel = remember {LeaderboardViewModel()}
    LeaderboardScreen(viewModel = LeaderboardViewModel())
    
}



