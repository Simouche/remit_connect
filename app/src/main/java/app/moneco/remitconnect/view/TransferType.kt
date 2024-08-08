package app.moneco.remitconnect.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.moneco.remitconnect.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferTypeScreen(goBack: () -> Unit, navigationAction: () -> Unit) {
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
                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "Send to Africa", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Surface(modifier = Modifier.height(20.dp)) {}

            SimpleDivider()
            ListItem(R.drawable.arrow_square_right_f, "Mobile wallets", navigationAction)

            SimpleDivider()
            ListItem(R.drawable.arrow_square_right_f, "Bank transfer") {}

            SimpleDivider()
        }
    }
}