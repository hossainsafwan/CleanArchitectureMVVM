package com.example.cleanarchitecture.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cleanarchitecture.ui.models.CountryUIModel

@Composable
fun CountryList(countryList: List<CountryUIModel>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(countryList) { countryItem ->
            CountryRow(country = countryItem)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryListPreview() {
    CountryList(countryList = List(3) { index ->
        CountryUIModel(countryName = "United States", countryCode = "USA", imageURL = "")
    })
}