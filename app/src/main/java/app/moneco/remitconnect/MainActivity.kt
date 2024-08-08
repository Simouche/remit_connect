package app.moneco.remitconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import app.moneco.remitconnect.ui.theme.RemitConnectTheme
import app.moneco.remitconnect.view.HomePage
import app.moneco.remitconnect.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemitConnectTheme {
                ApplicationNavGraph(viewModel = viewModel)
            }
        }
    }
}

