package com.example.cleanarchitecture.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.ui.models.CountryUIModel

@Composable
fun CountryRow(country: CountryUIModel, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth().wrapContentHeight()
            .padding(all = 8.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(country.imageURL)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.country_flag_image),
            placeholder = rememberVectorPainter(Icons.Filled.Flag),
            modifier = Modifier
                .size(32.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(text = country.countryName, modifier = Modifier.fillMaxWidth())
            Text(
                text = country.countryCode,
                fontSize = 8.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryRowPreview() {
    CountryRow(
        country = CountryUIModel(
            countryName = "United States",
            countryCode = "USA",
            imageURL = "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/images/CA.svg"
        )
    )
}