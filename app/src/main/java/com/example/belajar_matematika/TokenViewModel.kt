package com.example.belajar_matematika

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.belajar_matematika.role_guru.ApiClient
import kotlinx.coroutines.launch

class TokenViewModel: ViewModel() {
    private val _tokenSoal = mutableStateOf<String?>(null)
    val tokenSoal: State<String?> = _tokenSoal

    fun loadTokenSoal() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getToken()
                if (response.isSuccessful) {
                    val tokenResponse = response.body()?.responseToken?.getOrNull(0)
                    _tokenSoal.value = tokenResponse?.tokenSoal
                    Log.d("TokenViewModel", "Token berhasil diambil: $_tokenSoal")
                } else {
                    // Handle unsuccessful response
                    _tokenSoal.value = null
                    Log.e("TokenViewModel", "Gagal mengambil token: ${response.message()}")
                }
            } catch (e: Exception) {
                // Handle exception
                _tokenSoal.value = null
                Log.e("TokenViewModel", "Error: ${e.message}")
            }
        }
    }
}
