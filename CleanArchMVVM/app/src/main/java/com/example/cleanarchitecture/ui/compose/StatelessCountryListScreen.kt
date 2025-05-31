package com.example.cleanarchitecture.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.models.CountryUIModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessCountryListScreen(
    countryList: List<CountryUIModel>,
    searchValue: String,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onValueChange: (String) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { keyboardController?.hide() }
        ) {
            TextField(
                value = searchValue,
                label = {
                    Text(text = stringResource(R.string.search_hint))
                },
                onValueChange = { query ->
                    onValueChange(query)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                maxLines = 1
            )
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = onRefresh,
                modifier = Modifier.fillMaxSize()
            ) {
                CountryList(countryList = countryList, modifier = Modifier.padding(top = 8.dp))
            }

        }
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier)
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun StatelessCountryListScreenPreview() {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var isRefreshing by rememberSaveable { mutableStateOf(false) }

    StatelessCountryListScreen(
        countryList = List(50) { index ->
            CountryUIModel(
                countryName = "Canada",
                countryCode = "CN",
                imageURL = ""
            )
        },
        searchValue = searchQuery,
        isLoading = isLoading,
        onValueChange = { query ->
            searchQuery = query
        },
        onRefresh = { isRefreshing = isRefreshing.not() },
        isRefreshing = isRefreshing
    )

}