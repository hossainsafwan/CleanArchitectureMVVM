package com.example.cleanarchitecture.ui.views

import androidx.recyclerview.widget.DiffUtil
import com.example.cleanarchitecture.domain.models.Country

class CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country) = oldItem.countryCode == newItem.countryCode
    override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
}