package app.moneco.remitconnect.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.moneco.remitconnect.ui.theme.DarkGreen
import app.moneco.remitconnect.ui.theme.LightGreen
import app.moneco.remitconnect.ui.theme.LightGreen2
import app.moneco.remitconnect.viewmodel.AppViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyForm(goBack: () -> Unit, viewModel: AppViewModel, navigationActions: () -> Unit) {
    val amount by viewModel.amount.observeAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

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
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
                .padding(horizontal = 16.dp)

        ) {

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                ) {
                    ConfirmationBottomSheet(amount ?: 0.0f, {
                        showBottomSheet = false
                        navigationActions()
                    }, viewModel)
                }
            }

            Text(
                text = "Send Money",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "How much are you sending ?",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            TransferAmountInput(amount, viewModel)
            Spacer(modifier = Modifier.height(20.dp))
            RemittanceNotice()
            Spacer(modifier = Modifier.height(20.dp))
            FeesBreakdown(amount ?: 0.0f, viewModel)
            Spacer(modifier = Modifier.height(50.dp))
            ElevatedButton(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    if (amount != null && amount!! > 0) {
                        showBottomSheet = true
                    }
                },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = DarkGreen)
            ) {
                Text(text = "Send", color = Color.White)
            }
        }
    }
}

@Composable
fun TransferAmountInput(amount: Float?, viewModel: AppViewModel) {
    val isPositive = amount != null && amount > 0

    Card(
        modifier = Modifier.fillMaxWidth(), border = BorderStroke(
            1.dp,
            if (!isPositive) Color.LightGray else DarkGreen
        ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = amount.toString(),
                textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                onValueChange = { viewModel.amount.value = it.toFloat() },
                placeholder = { Text(text = "00", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                trailingIcon = {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "EUR",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.LightGray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                )
            )
            HorizontalDivider(color = if (!isPositive) Color.LightGray else LightGreen2)
            Box(
                modifier = Modifier
                    .background(color = if (!isPositive) Color.LightGray else LightGreen)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("your current balance is ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("${viewModel.currentBalance.value} EUR")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    color = if (!isPositive) Color.Black else DarkGreen,
                )
            }
        }
    }
}


@Composable
fun RemittanceNotice() {
    val ctx = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "How much are you sending ?",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Remittances are free with Moneco until you reach your limit, which resets every year.",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.LightGray,
                fontSize = 14.sp,
            ),
        )
        Text(
            text = "Check number of free remittance remaining",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                fontSize = 14.sp
            ),
            modifier = Modifier.clickable {
                Toast.makeText(ctx, "Coming soon", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun FeesBreakdown(amount: Float = 0.0f, appViewModel: AppViewModel) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Fees breakdown",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(Modifier.height(20.dp))
        BreakdownRow(title = "Moneco fees", amount = "0.0")
        Spacer(Modifier.height(20.dp))
        BreakdownRow(title = "Transfer fees", amount = "0.0")
        Spacer(Modifier.height(20.dp))
        BreakdownRow(title = "Conversion rate", amount = "655.94", "XOF")
        Spacer(Modifier.height(20.dp))
        BreakdownRow(title = "You'll spend in total", amount = "$amount")
        Spacer(Modifier.height(20.dp))

        DottedDivider()
        Spacer(Modifier.height(20.dp))
        BreakdownRow(title = "Recipient gets", amount = appViewModel.convertFromEuro(), "XOF")
    }
}

@Composable
fun BreakdownRow(title: String, amount: String, currency: String = "EUR") {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 14.sp,
            ),
        )
        Text(
            text = "$amount $currency", style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 14.sp,
            )
        )
    }
}

@Composable
fun DottedDivider() {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {

        drawLine(
            color = Color.DarkGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}

@Composable
fun ConfirmationBottomSheet(
    amount: Float,
    navigationAction: () -> Unit,
    appViewModel: AppViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Confirm transfer",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "You are sending",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "${appViewModel.convertFromEuro()} XOF",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "To",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Text(
            text = "${appViewModel.recipient.value?.name}",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Via",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Text(
            text = "${appViewModel.recipient.value?.mobileWallet}: +229 98 767 289",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(30.dp))
        ElevatedButton(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = navigationAction,
            colors = ButtonDefaults.elevatedButtonColors(containerColor = DarkGreen)
        ) {
            Text(text = "Continue", color = Color.White)
        }
    }
}