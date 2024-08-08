package app.moneco.remitconnect.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://my-json-server.typicode.com/MonecoHQ/fake-api/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val getAppService: AppService by lazy {
        retrofit.create(AppService::class.java)
    }
}