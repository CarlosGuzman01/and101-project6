package com.example.and101_project6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(val pokemonList: MutableList<String>, val pokemonNameList: MutableList<String>, val pokemonTypeList: MutableList<String>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val pokemonImage: ImageView
        val pokemonName: TextView
        val pokemonType: TextView

        init{
            pokemonImage = view.findViewById(R.id.pokemon_image)
            pokemonName = view.findViewById(R.id.pokemon_name)
            pokemonType = view.findViewById(R.id.pokemon_type)
        }
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
         return ViewHolder(view)
     }

     override fun getItemCount(): Int {
         return pokemonList.size
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {

         holder.pokemonName.text = pokemonNameList[position]
         holder.pokemonType.text = pokemonTypeList[position]

         Glide.with(holder.itemView)
             .load(pokemonList[position])
             .placeholder(R.drawable.pokeball)
             .centerCrop()
             .into(holder.pokemonImage)


     }

 }