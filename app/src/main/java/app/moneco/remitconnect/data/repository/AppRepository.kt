package app.moneco.remitconnect.data.repository

import app.moneco.remitconnect.data.api.RetrofitClient
import app.moneco.remitconnect.data.model.Country
import app.moneco.remitconnect.data.model.Recipient
import app.moneco.remitconnect.data.model.Wallet

class AppRepository {
    private val appService = RetrofitClient.getAppService

    suspend fun getWallets(): List<Wallet> = appService.getWallets()

    suspend fun getCountries(): List<Country> = appService.getCountries()

    suspend fun getRecipients(): List<Recipient> = appService.getRecipients()
}