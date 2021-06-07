package com.arivero007.myheroapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.myheroapp.databinding.HeroesRecyclerviewBinding
import com.arivero007.myheroapp.model.Heroe
import com.arivero007.myheroapp.model.HeroeListener
import java.util.*
import kotlin.collections.ArrayList

class HeroesAdapter(private val heroListener: HeroeListener): RecyclerView.Adapter<HeroesAdapter.HeroesHolder>(),
    Filterable {

    private lateinit var heroesHolder: HeroesHolder
    private lateinit var context: Context
    var heroes = ArrayList<Heroe>()
    var filteredItems = heroes

    private lateinit var binding: HeroesRecyclerviewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesHolder {

        context = parent.context
        binding = HeroesRecyclerviewBinding.inflate(LayoutInflater.from(context), parent, false)
        heroesHolder = HeroesHolder(binding)

        return heroesHolder
    }

    override fun onBindViewHolder(holder: HeroesHolder, position: Int) {

        val item = filteredItems[position]

        holder.name.text = item.name
        holder.heroe.setOnClickListener {
            heroListener.onHeroClick(item.id)
        }
    }

    override fun getItemCount(): Int {
        return filteredItems.count()
    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(text: CharSequence?): FilterResults {
                val searchText = text.toString()

                filteredItems = if(searchText.isEmpty()){
                    heroes
                }else{
                    val temp = ArrayList<Heroe>()

                    for (item in heroes) {
                        if (item.name.lowercase(Locale.ROOT).contains(text.toString())){
                            temp.add(item)
                        }
                    }
                    temp
                }
                val results = FilterResults()
                results.values = filteredItems
                return results
            }

            override fun publishResults(text: CharSequence?, results: FilterResults?) {

                filteredItems = results?.values as ArrayList<Heroe>
                notifyDataSetChanged()
            }

        }
    }

    fun updateData(data: List<Heroe>){
        heroes.clear()
        filteredItems.clear()
        heroes.addAll(data)
        filteredItems.addAll(data)
        notifyDataSetChanged()
    }

    class HeroesHolder(binding: HeroesRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){

        var name: TextView = binding.heroeName
        var heroe: ConstraintLayout = binding.heroeItem

    }

}