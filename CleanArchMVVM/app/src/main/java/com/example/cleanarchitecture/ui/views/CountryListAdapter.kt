package com.example.cleanarchitecture.ui.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.data.models.Country
import com.example.cleanarchitecture.databinding.CountryItemBinding

class CountryListAdapter :
    ListAdapter<Country, CountryListAdapter.CountryViewHolder>(CountryDiffUtil()) {

    inner class CountryViewHolder(private val binding: CountryItemBinding) :
        ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.countryName.text = country.name
            binding.code.text = country.code
            val imageLoader = ImageLoader.Builder(binding.flagIcon.context)
                .components {
                    add(SvgDecoder.Factory())
                }
                .build()
            binding.flagIcon.load(country.image, imageLoader) {
                error(
                    binding.flagIcon.context.resources.getDrawable(
                        R.drawable.ic_flag, null
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}