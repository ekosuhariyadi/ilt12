package com.codangcoding.ilt10.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.codangcoding.ilt10.R
import com.codangcoding.ilt10.databinding.ListItemPokemonBinding
import com.codangcoding.ilt10.presentation.model.PokemonVO

class PokemonVOAdapter(
    private val clickHandler: (PokemonVO) -> Unit
) : PagingDataAdapter<PokemonVO, PokemonVOAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<PokemonVO>() {
        override fun areItemsTheSame(oldItem: PokemonVO, newItem: PokemonVO): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: PokemonVO, newItem: PokemonVO): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding).apply {
            itemView.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition)?.let(clickHandler::invoke)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    inner class ViewHolder(private val binding: ListItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: PokemonVO) = with(binding) {
            tvName.text = pokemon.name
            ivPokemon.load(pokemon.imageUrl) {
                placeholder(R.mipmap.ic_launcher_round)
            }
        }
    }

}