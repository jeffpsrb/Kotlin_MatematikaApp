package com.example.belajar_matematika.role_guru

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object ApiClient {
    private const val BASE_URL = "http://192.168.166.90:3000/api/role_guru/"

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}


interface ApiService {
    @POST("guru")
    suspend fun dataGuru (
        @Body guruRequest: GuruRequest
    ):Response<GuruResponse>

    @GET("leaderboard")
    suspend fun getLeaderboard () : Response<LeaderboardResponse>

    @GET("token")
    suspend fun getToken (): Response<TokenResponse>

}

data class GuruRequest(
    val token_soal: String,
    val question: String,
    val true_answer: String
)

data class LeaderboardResponse(
    @SerializedName("response") val response :List<LeaderboardData>

)
data class LeaderboardData(
    @SerializedName("identitas_siswa") val identitasSiswa: String
)

data class  TokenResponse(
    @SerializedName("response") val responseToken: List<TokenData>
)
data class TokenData(
    @SerializedName("token_soal") val tokenSoal:String
)
data class GuruResponse(
    val message: String
)