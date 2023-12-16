package com.example.cleanarchitecture.ui.views

import androidx.recyclerview.widget.DiffUtil
import com.example.cleanarchitecture.data.models.Country

class CountryDiffUtil : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country) = oldItem.code == newItem.code
    override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
}