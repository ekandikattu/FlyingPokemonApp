package com.example.flyingpokemonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var typeList: MutableList<String>
    private lateinit var nameList: MutableList<String>
    private lateinit var moveList: MutableList<String>

    private lateinit var rvFlyingPokemon: RecyclerView


    private fun getFlyingPokemon(){
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/type/3", object: JsonHttpResponseHandler(){
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val FlyingPokemonArray = json.jsonObject.getJSONArray("pokemon")
                for (i in 0 until  31){
                    typeList.add(json.jsonObject.getString("name"))
                    nameList.add(FlyingPokemonArray.getJSONObject(i).getJSONObject("pokemon").getString("name"))
                    moveList.add(json.jsonObject.getJSONArray("moves").getJSONObject(i).getString("name"))
                }
                val adapter = FlyingPokemonAdapter(typeList, nameList, moveList)
                rvFlyingPokemon.adapter = adapter
                rvFlyingPokemon.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers,
                response: String,
                throwable: Throwable
            ) {
                Log.d("Flying Pokemon Error", response)
            }
        }]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFlyingPokemon()
        rvFlyingPokemon = findViewById(R.id.pokemonList)
        typeList = mutableListOf()
        nameList = mutableListOf()
        moveList = mutableListOf()
        rvFlyingPokemon.adapter = FlyingPokemonAdapter(typeList, nameList, moveList)
        rvFlyingPokemon.layoutManager = LinearLayoutManager(this)
        getFlyingPokemon()

    }
}