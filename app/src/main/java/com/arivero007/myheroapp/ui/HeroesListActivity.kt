package com.arivero007.myheroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arivero007.myheroapp.R
import com.arivero007.myheroapp.adapters.HeroesAdapter
import com.arivero007.myheroapp.databinding.ActivityMainBinding
import com.arivero007.myheroapp.network.RetrofitBuilder
import com.arivero007.myheroapp.resources.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroesListActivity : AppCompatActivity() {

    private val TAG = "HeroesListActivity: "

    private val heroesModel: HeroesViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: HeroesAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var binding: ActivityMainBinding

    //LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        heroesModel.heroes.observe(this, {
            if(it != null){
                if (!this::viewAdapter.isInitialized){
                    setUpRecyclerView()
                }else{
                    viewAdapter.notifyDataSetChanged()
                }
            }
        })

        downloadListOfHeroes()
    }

    override fun onResume() {
        super.onResume()

        LoadingDialog.getInstance(this).dismissLoadingDialog()
        //Listen for model

    }

    //REST
    private fun downloadListOfHeroes(){

        val ts = System.currentTimeMillis()/1000
        val hash = Utils.getMD5(ts.toString() + Constants.apiKeyPri + Constants.apiKeyPu)

        val retrofit = RetrofitBuilder
        retrofit.apiService.getListOHeroes(ts.toString(),Constants.apiKeyPu, hash).enqueue(
                object: Callback<HeroesList> {
                    override fun onResponse(call: Call<HeroesList>, response: Response<HeroesList>) {
                        val res = response.body()

                        Log.d(TAG, "Response correct!")

                        if (res != null){
                            heroesModel.setHeroes(res)
                        }
                    }

                    override fun onFailure(call: Call<HeroesList>, t: Throwable) {
                        Log.d(TAG, "Web Service failed!")
                    }

                })
    }

    //RecyclerView
    fun setUpRecyclerView(){
        recyclerView = binding.heroesRecyclerview
        viewManager = LinearLayoutManager(this)
        viewAdapter = HeroesAdapter(LoadingDialog.getInstance(this), this, heroesModel.heroes.value!!.data.results)
        recyclerView.layoutManager = viewManager
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20);
        recyclerView.adapter = viewAdapter
    }

    //Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val item = menu?.findItem(R.id.search_item)
        val search = item?.actionView as SearchView

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewAdapter.filter.filter(newText)
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.search_item -> {
                true
            }
            R.id.refresh_item ->{
                downloadListOfHeroes()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}