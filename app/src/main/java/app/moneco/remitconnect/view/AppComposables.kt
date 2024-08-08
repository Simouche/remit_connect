package app.moneco.remitconnect.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(icon: Int, title: String, navigationAction: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navigationAction.invoke() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color(0xFF9EECC6),
            ),
            shape = RoundedCornerShape(16),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = title,
            )
        }
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun SimpleDivider() {
    HorizontalDivider(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth(),
        color = Color.LightGray
    )
}