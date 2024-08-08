package app.moneco.remitconnect.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.moneco.remitconnect.R
import app.moneco.remitconnect.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectWalletScreen(
    goBack: () -> Unit,
    appViewModel: AppViewModel,
    navigationAction: () -> Unit
) {
    val wallets by appViewModel.wallets.observeAsState()

    LaunchedEffect(Unit) {
        appViewModel.getWallets()
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    OutlinedButton(
                        onClick = goBack,
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color.LightGray),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.LightGray)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "back",
                        )
                    }
                })
        },
    ) {
        Column(
            Modifier
                .padding( start = 16.dp, end = 16.dp)
                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
        ) {
            Text(text = "Choose a mobile wallet", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))

            if (wallets == null) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator()
                }
            } else { // keeping this one hardcoded for the sake of the icons, since the API doesn't have icons.
                WalletItem(R.drawable.wave, "Wave", navigationAction)
                Spacer(modifier = Modifier.height(20.dp))
                WalletItem(R.drawable.mtn_mobile_money, "MTN Mobile Money", navigationAction)
                Spacer(modifier = Modifier.height(20.dp))
                WalletItem(R.drawable.orange_money, "Orange Money", navigationAction)
            }

        }
    }
}

@Composable
fun WalletItem(icon: Int, title: String, navigationAction: () -> Unit) {

    Box(
        modifier = Modifier
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(16.dp))
            .clickable { navigationAction.invoke() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = icon),
                contentDescription = title,
            )

            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}