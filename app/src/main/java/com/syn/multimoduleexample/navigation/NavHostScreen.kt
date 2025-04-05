package com.syn.multimoduleexample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.feature.home.HomeRoute
import com.feature.home.homeScreenNavigation
import com.feature.search.SearchRoute
import com.feature.search.searchScreenNavigation

@Composable
fun MainNavigationScreen(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute
    ) {

        homeScreenNavigation(
            goToSearch = {
                navController.navigate(SearchRoute){
                    launchSingleTop = true
                }
            }
        )

        searchScreenNavigation(
            navigateBack = navController::navigateUp
        )

    }
}