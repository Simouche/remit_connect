package app.moneco.remitconnect.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.moneco.remitconnect.R
import app.moneco.remitconnect.data.model.Country
import app.moneco.remitconnect.data.model.Recipient
import app.moneco.remitconnect.ui.theme.DarkGreen
import app.moneco.remitconnect.ui.theme.DarkGreen2
import app.moneco.remitconnect.ui.theme.LightGreen
import app.moneco.remitconnect.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectRecipientScreen(
    goBack: () -> Unit,
    viewModel: AppViewModel,
    navigationAction: () -> Unit
) {

    val listToggle by viewModel.listToggle.observeAsState()
    val selectedRecipient by viewModel.recipient.observeAsState()

    if (selectedRecipient != null)
        navigationAction.invoke()

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
                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Who are you sending to?", fontWeight = FontWeight.Bold, fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp),
            )
            ToggleListButton(viewModel, listToggle)

            if (listToggle == 0)
                SelectRecipientBody(viewModel)
            else
                AddRecipientBody(viewModel)
        }
    }
}

@Composable
fun ToggleListButton(viewModel: AppViewModel, listToggle: Int?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            onClick = { viewModel.listToggle.value = 0 },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (listToggle == 0) DarkGreen else Color(0xFF9EECC6)
            ),
            shape = RoundedCornerShape(8.dp) // Adjust corner radius as needed
        ) {
            Text("Previous recipients", color = if (listToggle == 0) Color.White else DarkGreen)
        }
        Button(
            onClick = { viewModel.listToggle.value = 1 },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (listToggle == 1) DarkGreen else Color(0xFF9EECC6)
            ),
            shape = RoundedCornerShape(8.dp) // Adjust corner radius as needed
        ) {
            Text("New recipient", color = if (listToggle == 1) Color.White else DarkGreen)
        }
    }
}

@Composable
fun SelectRecipientBody(viewModel: AppViewModel) {
    val recipients by viewModel.recipients.observeAsState()

    val searchQuery by viewModel.searchQuery.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getRecipients()

    }

    val rcpList = if (searchQuery != null) viewModel.search(searchQuery!!) else recipients

    if (rcpList == null) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CircularProgressIndicator()
        }
    } else {
        RecipientsList(recipients = rcpList, viewModel = viewModel)
    }
}


@Composable
fun RecipientsList(recipients: List<Recipient>, viewModel: AppViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        TextField(
            value = viewModel.searchQuery.value ?: "",
            onValueChange = { viewModel.searchQuery.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(16.dp),
            placeholder = { Text(text = "Search...") },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search, contentDescription = "no result"
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color(0xFF9EECC6),
                unfocusedContainerColor = Color.LightGray,
            )

        )

        if (recipients.isEmpty()) {
            Image(
                painterResource(id = R.drawable.no_result), contentDescription = "no result"
            )
        } else {
            Text(
                text = "Contacts on your phone",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            recipients.forEach {
                SimpleDivider()
                RecipientItem(recipient = it, viewModel = viewModel)
            }
            SimpleDivider()
        }
    }
}


@Composable
fun RecipientItem(recipient: Recipient, viewModel: AppViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { viewModel.recipient.value = recipient },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = Color(0xFF9EECC6),
        ), shape = RoundedCornerShape(16), onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.user_square),
                contentDescription = recipient.name,
            )
        }
        Column {
            Text(
                text = recipient.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = "+229 98 767 289",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Gray
            )

        }
    }
}

@Composable
fun AddRecipientBody(viewModel: AppViewModel) {
    //TODO improve this after finish main scope

    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var phoneNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    var isOpen by remember { mutableStateOf(false) }

    val countries by viewModel.countries.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getCountries()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DropdownMenu(
            expanded = isOpen,
            onDismissRequest = { isOpen = false }
        ) {
            countries?.forEach { country ->
                DropdownMenuItem(
                    onClick = {
                        selectedCountry = country
                        isOpen = false
                    },
                    text = { Text(text = country.name) })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Choose a contact button
        OutlinedButton(
            onClick = { /* handle contact selection */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, DarkGreen2),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = DarkGreen,
            ),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("CHOOSE A CONTACT")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Phone number input
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* handle next action */ }
            ),
            placeholder = { Text("Phone number") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // First name input
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* handle next action */ }
            ),
            placeholder = { Text("First Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Last name input
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* handle done action */ }
            ),
            placeholder = { Text("Last Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Continue button
        OutlinedButton(
            onClick = { /* handle continue button click */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, DarkGreen2),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightGreen,
                contentColor = DarkGreen,
            ),
        ) {
            Text("Continue")
        }
    }
}