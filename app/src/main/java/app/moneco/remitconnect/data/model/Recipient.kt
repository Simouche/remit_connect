package app.moneco.remitconnect.data.model


import com.google.gson.annotations.SerializedName

data class Recipient(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("mobile_wallet")
    val mobileWallet: String,
    @SerializedName("name")
    val name: String
)