package com.example.cleanarchitecture.ui.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CountryList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(count = 3) {
            CountryRow()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryListPreview() {
    CountryList()
}