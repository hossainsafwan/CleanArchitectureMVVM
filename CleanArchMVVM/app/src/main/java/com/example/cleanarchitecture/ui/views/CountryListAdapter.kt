package com.example.cleanarchitecture.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.CountryItemBinding
import com.example.cleanarchitecture.ui.models.CountryUIModel

class CountryListAdapter :
    ListAdapter<CountryUIModel, CountryListAdapter.CountryViewHolder>(CountryDiffUtil()) {

    inner class CountryViewHolder(private val binding: CountryItemBinding) :
        ViewHolder(binding.root) {
        fun bind(country: CountryUIModel) {
            binding.countryName.text = country.countryName
            binding.code.text = country.countryCode
            val imageLoader = ImageLoader.Builder(binding.flagIcon.context)
                .components {
                    add(SvgDecoder.Factory())
                }
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .logger(DebugLogger())
                .build()

            binding.flagIcon.load(country.imageURL, imageLoader) {
                error(
                    AppCompatResources.getDrawable(binding.flagIcon.context,R.drawable.ic_flag)
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