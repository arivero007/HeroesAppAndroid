package com.arivero007.myheroapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.myheroapp.databinding.HeroesRecyclerviewBinding
import com.arivero007.myheroapp.resources.Heroe
import com.arivero007.myheroapp.resources.LoadingDialog
import com.arivero007.myheroapp.ui.HeroeActivity
import java.util.*
import kotlin.collections.ArrayList

class HeroesAdapter(private val hud: LoadingDialog, private val context: Context, private val heroes: List<Heroe>): RecyclerView.Adapter<HeroesAdapter.HeroesHolder>(),
    Filterable {

    private lateinit var heroesHolder: HeroesHolder
    var filteredItems = heroes

    private lateinit var binding: HeroesRecyclerviewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesHolder {

        binding = HeroesRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        heroesHolder = HeroesHolder(binding)

        return heroesHolder
    }

    override fun onBindViewHolder(holder: HeroesHolder, position: Int) {

        val heroe = filteredItems[position]

        holder.name.text = heroe.name
        holder.heroe.setOnClickListener {

            hud.startLoadingDialog()

            val intent = Intent( context, HeroeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("heroeId", heroe.id)
            context.startActivity(intent)

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
                        if (item.name.toLowerCase(Locale.ROOT).contains(text.toString())){
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

                filteredItems = results?.values as List<Heroe>
                notifyDataSetChanged()
            }

        }
    }

    class HeroesHolder(binding: HeroesRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){

        var name: TextView = binding.heroeName
        var heroe: ConstraintLayout = binding.heroeItem

    }

}