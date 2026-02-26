package pet.dolphin.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pet.dolphin.home.presentation.balance.HomeBalance
import pet.dolphin.home.presentation.components.TopHomeBar
import pet.dolphin.home.presentation.balance.model.BalanceUI
import pet.dolphin.home.presentation.fund.MyFundItem
import pet.dolphin.home.presentation.fund.SuggestFundItem
import pet.dolphin.home.presentation.fund.model.DisplayableNumber
import pet.dolphin.home.presentation.fund.model.FundUI
import pet.dolphin.ui.R
import pet.dolphin.home.R as LocalR
import pet.dolphin.core.ui.ThemeShapes
import pet.eat.ui.LocalColorsPalette

@Composable
fun HomeScreen() {
    val funds = listOf(
        FundUI(
            symbol = "BTC",
            fullName = "Bitcoin",
            logoImg = "none",
            totalCoinsPrice = DisplayableNumber(
                value = 10.42,
                formatted = "$10.42"  // 👈 Включаем символ валюты
            ),
            changePercent24Hr = DisplayableNumber(
                value = 0.7,
                formatted = "+0.7%"
            ),
            changeCurrency24Hr = DisplayableNumber(
                value = 0.84,
                formatted = "+$0.84"
            ),
            localCurrencySymbol = stringResource(R.string.dollar_marker)
        ),
        FundUI(
            symbol = "PXM",
            fullName = "Postmarker",
            logoImg = "none",
            totalCoinsPrice = DisplayableNumber(
                value = 20.01,
                formatted = "$20.01"  // 👈 Символ валюты
            ),
            changePercent24Hr = DisplayableNumber(
                value = 10.7,
                formatted = "+10.7%"
            ),
            changeCurrency24Hr = DisplayableNumber(
                value = 2.10,
                formatted = "+$2.10"
            ),
            localCurrencySymbol = stringResource(R.string.dollar_marker)
        ),
        FundUI(
            symbol = "NON",
            fullName = "None Bitcoin",
            logoImg = "none",
            totalCoinsPrice = DisplayableNumber(
                value = 5004.05,
                formatted = "$5,004.05"  // 👈 Символ валюты
            ),
            changePercent24Hr = DisplayableNumber(
                value = -10.0,
                formatted = "-10.0%"
            ),
            changeCurrency24Hr = DisplayableNumber(
                value = -500.35,
                formatted = "-$500.35"
            ),
            localCurrencySymbol = stringResource(R.string.dollar_marker)
        ),
        FundUI(
            symbol = "COE",
            fullName = "CoeCoe Coin",
            logoImg = "none",
            totalCoinsPrice = DisplayableNumber(
                value = 1778.00,
                formatted = "$1,778.00"  // 👈 Символ валюты
            ),
            changePercent24Hr = DisplayableNumber(
                value = 11.0,
                formatted = "+11.0%"
            ),
            changeCurrency24Hr = DisplayableNumber(
                value = 199.89,
                formatted = "+$199.89"
            ),
            localCurrencySymbol = stringResource(R.string.dollar_marker)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LocalColorsPalette.current.background)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            // Top Bar (Profile & Navigation)
            TopHomeBar(
                "",
                "Smart People"
            )

            Spacer(Modifier.height(20.dp))

            val basicModifierContainer = Modifier
                .clip(ThemeShapes.medium)
                .background(LocalColorsPalette.current.container)

            // Widget Balance
            HomeBalance(
                modifier = basicModifierContainer,
                balanceUi = BalanceUI(
                    id = "1asff",
                    totalCoinsPrice = DisplayableNumber(
                        value = 27421.42,
                        formatted = "$27,421.42"
                    ),
                    changePercent24Hr = DisplayableNumber(
                        value = 2.7,
                        formatted = "+2.7%"
                    ),
                    changeCurrency24Hr = DisplayableNumber(
                        value = 741.35,
                        formatted = "+$741.35"
                    ),
                    localCurrencySymbol = stringResource(R.string.dollar_marker)
                )
            )

            Spacer(Modifier.height(20.dp))

            // My Funds title
            Row {
                Text(stringResource(LocalR.string.my_fund_title),
                    color = LocalColorsPalette.current.onContainer,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(Modifier.weight(1f))

                Text(stringResource(LocalR.string.view_all),
                    color = LocalColorsPalette.current.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    modifier = Modifier.wrapContentHeight(),
                    tint = LocalColorsPalette.current.onBackground,
                    contentDescription = "view_all_ic"
                )
            }

            // List My Funds
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(funds){ curFund ->
                    MyFundItem(
                        modifier = basicModifierContainer
                            .padding(vertical = 10.dp, horizontal = 12.dp),
                        fundUi = curFund
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Suggest Funds title
            Row {
                Text(stringResource(LocalR.string.suggest_title),
                    color = LocalColorsPalette.current.onContainer,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(Modifier.weight(1f))

                Text(stringResource(LocalR.string.view_all),
                    color = LocalColorsPalette.current.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    modifier = Modifier.wrapContentHeight(),
                    tint = LocalColorsPalette.current.onBackground,
                    contentDescription = "view_all_ic"
                )
            }

            // List Suggest Funds
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(funds){ curFund ->
                    SuggestFundItem(
                        modifier = basicModifierContainer
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 12.dp),
                        fundUi = curFund
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}