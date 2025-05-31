package com.example.cleanarchitecture.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
    onValueChange: (String) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

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
            isRefreshing = isLoading,
            onRefresh = onRefresh,
            modifier = Modifier
        ) {
            CountryList(countryList = countryList, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatelessCountryListScreenPreview() {
    var searchQuery by rememberSaveable { mutableStateOf("") }
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
        isLoading = isRefreshing,
        onValueChange = { query ->
            searchQuery = query
        },
        onRefresh = { },
    )

}