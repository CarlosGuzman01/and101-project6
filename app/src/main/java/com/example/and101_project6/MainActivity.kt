package com.example.and101_project6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var pokemonImage = ""
    var pokemonName = ""
    var pokemonType = ""

    private lateinit var pokemonList: MutableList<String>
    private lateinit var pokemonNameList: MutableList<String>
    private lateinit var pokemonTypeList: MutableList<String>
    private lateinit var recyclerViewPokemon: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewPokemon = findViewById(R.id.pokemonRecyclerView)
        pokemonList = mutableListOf()
        pokemonNameList = mutableListOf()
        pokemonTypeList = mutableListOf()

        getRandomPokemon()

    }

    private fun getRandomPokemon(){

            for(i in 1..100) {


                val client = AsyncHttpClient()
                var clientString = getRandomClient()

                client[clientString, object : JsonHttpResponseHandler() {
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        response: String?,
                        throwable: Throwable?
                    ) {
                        Log.d("Pokemon Error", "$response")
                    }

                    override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {

                        if (json != null) {
                            pokemonImage =
                                json.jsonObject.getJSONObject("sprites").getString("front_default")
                        }


                        if (json != null) {
                            pokemonName = json.jsonObject.getString("name")
                        }


                        if (json != null) {
                            pokemonType = json.jsonObject.getJSONArray("types").getJSONObject(0)
                                .getJSONObject("type").getString("name")
                        }

                        val pokemonAdapter = PokemonAdapter(pokemonList, pokemonNameList, pokemonTypeList)
                        recyclerViewPokemon.adapter = pokemonAdapter
                        recyclerViewPokemon.layoutManager = LinearLayoutManager(this@MainActivity)



                        pokemonList.add(pokemonImage)
                        pokemonNameList.add(pokemonName)
                        pokemonTypeList.add(pokemonType)
                        var size = pokemonList.size
                        Log.d("size of the list: ", "$pokemonList.size")



                    }

                }]
            }

        recyclerViewPokemon.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                LinearLayoutManager.VERTICAL
            )
        )


    }

    private fun getRandomClient(): String{
        var clientString = "https://pokeapi.co/api/v2/pokemon/"

        val random = Random.Default
        val min = 1
        val max = 1010

        var randomInt = random.nextInt(min, max + 1).toString()

        clientString += randomInt

        return clientString

    }


}