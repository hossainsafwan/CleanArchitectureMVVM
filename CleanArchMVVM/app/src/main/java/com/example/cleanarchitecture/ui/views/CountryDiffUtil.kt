package com.example.cleanarchitecture.ui.views

import androidx.recyclerview.widget.DiffUtil
import com.example.cleanarchitecture.ui.models.CountryUIModel

class CountryDiffUtil : DiffUtil.ItemCallback<CountryUIModel>() {
    override fun areItemsTheSame(oldItem: CountryUIModel, newItem: CountryUIModel) =
        oldItem.countryCode == newItem.countryCode

    override fun areContentsTheSame(oldItem: CountryUIModel, newItem: CountryUIModel) =
        oldItem == newItem
}