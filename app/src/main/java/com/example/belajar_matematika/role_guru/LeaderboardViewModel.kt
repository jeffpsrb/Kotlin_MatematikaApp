package com.example.belajar_matematika.role_guru

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LeaderboardViewModel: ViewModel() {
    private val _identitasSiswa = mutableStateOf<List<LeaderboardData>>(emptyList())
    val identitasSiswa: State<List<LeaderboardData>> = _identitasSiswa

    fun loadIdentitasSiswa() {
        viewModelScope.launch {
            val response = ApiClient.apiService.getLeaderboard()
            if(response.isSuccessful){
                _identitasSiswa.value = response.body()?.response ?: emptyList()
            } else {
                emptyList<List<LeaderboardData>>()
            }
        }
    }


    fun refreshIdentitasSiswa() {
        loadIdentitasSiswa()
    }
}