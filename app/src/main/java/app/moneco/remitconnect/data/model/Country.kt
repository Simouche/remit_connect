package app.moneco.remitconnect.data.model


import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("currency_code")
    val currencyCode: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)