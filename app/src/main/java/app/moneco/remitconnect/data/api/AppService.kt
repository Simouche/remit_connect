package app.moneco.remitconnect.data.api

import app.moneco.remitconnect.data.model.Country
import app.moneco.remitconnect.data.model.Recipient
import app.moneco.remitconnect.data.model.Wallet
import retrofit2.http.GET

interface AppService {

    @GET("wallets")
    suspend fun getWallets(): List<Wallet>

    @GET("countries")
    suspend fun getCountries(): List<Country>

    @GET("recipients")
    suspend fun getRecipients(): List<Recipient>
}