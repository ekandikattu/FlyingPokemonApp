package com.example.flyingpokemonapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlyingPokemonAdapter(private val typeList: List<String>, private val nameList: List<String>, private val moveList: List<String>): RecyclerView.Adapter<FlyingPokemonAdapter.ViewHolder>(){
    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val typeName:TextView = view.findViewById(R.id.type_list)
        val nameName:TextView = view.findViewById(R.id.name_list)
        val moveName:TextView = view.findViewById(R.id.move_list)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlyingPokemonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlyingPokemonAdapter.ViewHolder, position: Int) {
        val type = typeList[position]
        val name = nameList[position]
        val move = moveList[position]

        holder.typeName.text = type
        holder.nameName.text = name
        holder.moveName.text = move
    }

    override fun getItemCount() = nameList.size

}