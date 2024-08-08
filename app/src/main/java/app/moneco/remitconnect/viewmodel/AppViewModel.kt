package app.moneco.remitconnect.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.moneco.remitconnect.data.model.Country
import app.moneco.remitconnect.data.model.Recipient
import app.moneco.remitconnect.data.model.Wallet
import app.moneco.remitconnect.data.repository.AppRepository
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class AppViewModel : ViewModel() {
    private val repository = AppRepository()

    private val _wallets = MutableLiveData<List<Wallet>>()
    val wallets: LiveData<List<Wallet>> = _wallets

    private val _recipients = MutableLiveData<List<Recipient>>()
    val recipients: LiveData<List<Recipient>> = _recipients

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val recipient = MutableLiveData<Recipient>()

    val searchQuery = MutableLiveData<String>()

    // 0 for Previous recipients
    // 1 for new recipient
    val listToggle = MutableLiveData<Int>(0)

    val amount = MutableLiveData<Float>(0.0f)
    val currentBalance = MutableLiveData<Float>(230f)

    fun convertFromEuro(conversionRate: Float = 655.94f): String {
        return NumberFormat.getCurrencyInstance()
            .format(amount.value?.times(conversionRate) ?: 0.0f)

    }

    fun search(searchQuery: String): List<Recipient> {
        if (searchQuery.isBlank() && searchQuery.isEmpty())
            return recipients.value ?: listOf()
        return recipients.value?.filter {
            it.name.lowercase().contains(searchQuery.lowercase())
        }?.toList() ?: listOf()
    }

    fun getWallets() {
        viewModelScope.launch {
            try {
                val wlts = repository.getWallets()
                _wallets.value = wlts
            } catch (e: Exception) {
                _errorMessage.value = "Error loading wallets, try again later."
                Log.d("Moneco app", "getWallets: ${e.message}")
            }
        }
    }

    fun getRecipients() {
        viewModelScope.launch {
            try {
                val rcps = repository.getRecipients()
                _recipients.value = rcps
            } catch (e: Exception) {
                _errorMessage.value = "Error loading recipients, try again later."
                Log.d("Moneco app", "getRecipients: ${e.message}")
            }
        }
    }

    fun getCountries() {
        viewModelScope.launch {
            try {
                val ctrs = repository.getCountries()
                _countries.value = ctrs
            } catch (e: Exception) {
                _errorMessage.value = "Error loading countries, try again later."
                Log.d("Moneco app", "getCountries: ${e.message}")
            }
        }
    }
}