package com.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.syn.presentation.CommonScaffold
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

fun NavGraphBuilder.homeScreenNavigation(
    goToSearch: () -> Unit,
) {
    composable<HomeRoute> {
        HomeScreen(
            goToSearch = goToSearch
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    goToSearch: () -> Unit
) {
    CommonScaffold(
        modifier = modifier,
        appBar = {
            TopAppBar(
                title = {
                    Text(
                        "Home",
                        modifier = Modifier,
                        color = Color.White
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Home Screen",
                modifier = Modifier.fillMaxWidth()
            )

            ElevatedButton(
                onClick = goToSearch,
            ) {
                Text(
                    text = "Go to Search",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}