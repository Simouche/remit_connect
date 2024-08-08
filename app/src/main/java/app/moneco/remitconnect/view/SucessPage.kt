package app.moneco.remitconnect.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.moneco.remitconnect.R
import app.moneco.remitconnect.ui.theme.DarkGreen
import app.moneco.remitconnect.ui.theme.SuccessGreen

@Composable
fun SuccessPage(navigationAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = SuccessGreen)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.money_sent_success),
            contentDescription = "Success"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Your money is on the way. Get Excited!",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = navigationAction,
            colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF007554))
        ) {
            Text(text = "Got it!", color = Color.White)
        }
    }
}