package com.example.belajar_matematika

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

object ApiClient {
    private const val BASE_URL = "http://192.168.100.159:3000/api/role_siswa/"

    val apiService:ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @Multipart
    @POST("siswa")
    suspend fun uploadData(
        @Part image: MultipartBody.Part,
        @Part("identitas_siswa") identitas: RequestBody
    ): Response<ResponseBody>
}

