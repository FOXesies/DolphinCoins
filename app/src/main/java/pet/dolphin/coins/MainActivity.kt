package pet.dolphin.coins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pet.dolphin.coins.ui.theme.DolphinCoinsTheme
import pet.dolphin.core.navigation.Screen
import pet.dolphin.home.presentation.HomeScreenRoot

class MainActivity : ComponentActivity() {
    private val backStack = mutableListOf<Screen>(Screen.Home)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DolphinCoinsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigationHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        backStack = backStack,
                        onNavigate = { screen ->
                            backStack.add(screen)
                        },
                        onBack = {
                            if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                        }
                    )
                }
            }
        }
    }



    @Composable
    fun AppNavigationHost(
        backStack: List<Screen>,
        onNavigate: (Screen) -> Unit,
        onBack: () -> Unit,
        modifier: Modifier
    ) {
        when (val screen = backStack.last()) {
            Screen.Home -> HomeScreenRoot(
                modifier,
                onNavigateDetail = { onNavigate(Screen.DetailFund(it)) }
            )
            is Screen.DetailFund -> {}/*DetailRoute(
            id = screen.id,
            onBack = onBack
        )*/
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DolphinCoinsTheme {
        Greeting("Android")
    }
}