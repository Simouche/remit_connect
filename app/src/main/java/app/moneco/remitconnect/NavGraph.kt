package app.moneco.remitconnect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.moneco.remitconnect.view.HomePage
import app.moneco.remitconnect.view.SelectRecipientScreen
import app.moneco.remitconnect.view.SelectWalletScreen
import app.moneco.remitconnect.view.SendMoneyForm
import app.moneco.remitconnect.view.SendMoneyPage
import app.moneco.remitconnect.view.SuccessPage
import app.moneco.remitconnect.view.TransferTypeScreen
import app.moneco.remitconnect.viewmodel.AppViewModel

@Composable
fun ApplicationNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME_ROUTE,
    viewModel: AppViewModel,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(Destinations.HOME_ROUTE) {
            HomePage { navActions.navigateToSendMoney() }
        }

        composable(Destinations.SEND_MONEY_ROUTE) {
            SendMoneyPage({ navActions.goBack() }) {
                navActions.navigateToSendToAfrica()
            }
        }

        composable(Destinations.SEND_TO_AFRICA_ROUTE) {
            TransferTypeScreen({navActions.goBack()}) {
                navActions.navigateToRecipientList()
            }
        }

        composable(Destinations.RECIPIENT_LIST_ROUTE) {
            SelectRecipientScreen({navActions.goBack()},viewModel) {
                navActions.navigateToSelectWallet()
            }
        }

        composable(Destinations.SELECT_WALLET_ROUTE) {
            SelectWalletScreen({navActions.goBack()}, viewModel) {
                navActions.navigateToSendMoneyForm()
            }
        }

        composable(Destinations.SEND_MONEY_FORM) {
            SendMoneyForm({navActions.goBack()}, viewModel) {
                navActions.navigateToSuccess()
            }
        }

        composable(Destinations.SUCCESS_ROUTE) {
            SuccessPage() {
                navActions.navigateHome()
            }
        }
    }
}

object Destinations {
    const val HOME_ROUTE = "home"
    const val SEND_MONEY_ROUTE = "send_money"
    const val SEND_TO_AFRICA_ROUTE = "send_to_africa"
    const val RECIPIENT_LIST_ROUTE = "recipient_list"
    const val SELECT_WALLET_ROUTE = "wallet_select"
    const val SEND_MONEY_FORM = "send_money_form"
    const val SUCCESS_ROUTE = "success"
}

class NavigationActions(private val navController: NavHostController) {
    fun goBack(){
        navController.popBackStack()
    }

    fun navigateHome() {
        navController.popBackStack(Destinations.HOME_ROUTE, inclusive = false)
    }

    fun navigateToSendMoney() {
        navController.navigate(Destinations.SEND_MONEY_ROUTE)
    }

    fun navigateToSendToAfrica() {
        navController.navigate(Destinations.SEND_TO_AFRICA_ROUTE)
    }

    fun navigateToRecipientList() {
        navController.navigate(Destinations.RECIPIENT_LIST_ROUTE)
    }

    fun navigateToSelectWallet() {
        navController.navigate(Destinations.SELECT_WALLET_ROUTE)
    }

    fun navigateToSendMoneyForm() {
        navController.navigate(Destinations.SEND_MONEY_FORM)
    }

    fun navigateToSuccess() {
        navController.navigate(Destinations.SUCCESS_ROUTE)
    }

}