package app.moneco.remitconnect.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.moneco.remitconnect.R
import app.moneco.remitconnect.ui.theme.DarkGreen
import app.moneco.remitconnect.ui.theme.DarkGreen2

@Composable
fun HomePage(navAction: () -> Unit) {


    Scaffold(
        bottomBar = { BottomNavigationBar(navAction) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Header("John Doe")
            BalanceCard("320,00", "US Dollars")
            HomeCards()
        }

    }
}

@Composable
fun Header(fullName: String, notificationCount: Int = 0) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Hey, $fullName", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        FilledIconButton(
            colors = IconButtonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.LightGray,
            ),
            shape = RoundedCornerShape(16),
            onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.bell_f),
                contentDescription = "notifications",
            )
        }
    }
}

@Composable
fun BalanceCard(balance: String, currency: String) {
    Card(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .height(160.dp), colors = CardDefaults.cardColors(containerColor = DarkGreen)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = "Your balance", color = Color.White)
                Column {
                    Text(
                        text = balance,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    Text(text = currency, color = Color.White)
                }
            }

            OutlinedButton(
                onClick = { /*DO NOTHING*/ },
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0XFF0F9D58)),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0XFF0F9D58))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.moneys),
                    contentDescription = "your balance",
                    tint = Color.Unspecified,
                )
            }
        }
    }
}

@Composable
fun HomeCards() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            HomeCard(icon = R.drawable.empty_wallet_add, title = "Top up balance")
            HomeCard(icon = R.drawable.wallet_minus, title = "Withdraw money")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            HomeCard(icon = R.drawable.card, title = "Get IBAN")
            HomeCard(icon = R.drawable.percentage_square, title = "View Analytics")
        }
    }

}

@Composable
fun HomeCard(icon: Int, title: String) {
    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxHeight()
            .width(170.dp)
            .padding(all = 16.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp),
        ) {
            OutlinedButton(
                onClick = { /*DO NOTHING*/ },
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFF9EECC6)),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = Color(0xFF9EECC6))
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "your balance",
                    tint = Color.Unspecified,
                )
            }
            Text(text = title, color = DarkGreen, fontSize = 14.sp)
        }
    }
}

@Composable
fun BottomNavigationBar(navAction: () -> Unit) {
    BottomAppBar {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home_f),
                    contentDescription = "home",
                )
            },
            label = { Text(text = "Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkGreen2,
                selectedTextColor = DarkGreen2,
                indicatorColor = Color.Transparent,
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.credit_card_f),
                    contentDescription = "Cards",
                )
            },
            label = { Text(text = "Cards") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkGreen2,
                selectedTextColor = DarkGreen2,
                indicatorColor = Color.Transparent,
            )
        )
        FloatingActionButton(
            containerColor = Color.Yellow,
            shape = CircleShape,
            modifier = Modifier.background(color = Color.Yellow, shape = CircleShape),
            onClick = navAction,
        ) {
            Image(
                modifier = Modifier.background(color = Color.Yellow, shape = CircleShape),
                painter = painterResource(id = R.drawable.paper_plane),
                contentDescription = "send"
            )
        }
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.coin_f),
                    contentDescription = "Tontines",
                )
            },
            label = { Text(text = "Tontines") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkGreen2,
                selectedTextColor = DarkGreen2,
                indicatorColor = Color.Transparent,
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.cog_f),
                    contentDescription = "Settings",
                )
            },
            label = { Text(text = "Settings") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = DarkGreen2,
                selectedTextColor = DarkGreen2,
                indicatorColor = Color.Transparent,
            )

        )
    }

}
