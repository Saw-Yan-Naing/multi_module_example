package com.feature.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.syn.presentation.CommonAppBar
import com.syn.presentation.CommonScaffold
import com.syn.presentation.CommonText
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

fun NavGraphBuilder.searchScreenNavigation(
    navigateBack: () -> Unit = {},
) {
    composable<SearchRoute> {
        SearchScreen(
            navigateBack = navigateBack,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {

    CommonScaffold(
        modifier = modifier,
        appBar = {
            CommonAppBar(
                title = "Search",
                onBackPressed = navigateBack
            )
        }
    ) {
        CommonText(
            text = "Search Screen",
            modifier = Modifier.fillMaxSize()
                .align(Alignment.Center),
        )
    }
}