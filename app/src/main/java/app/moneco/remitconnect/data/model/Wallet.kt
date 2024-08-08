package app.moneco.remitconnect.data.model


import com.google.gson.annotations.SerializedName

data class Wallet(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)