package com.cis2818.volleytest11_11

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.cis2818.volleytest11_11.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.StringRequest



class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //set button handler
        binding.btnGetCatData.setOnClickListener {

              //Fetch COIN Data from API
              fetchCoinData()

            Log.i("MainActivity","After Click Event")

        }//end click listener

    }//end fun OnCreate


    /**
     * FUNCTION: fetchCoinData
     */
    private fun fetchCoinData() {

        //RequestQueue object
        val requestQueue = Volley.newRequestQueue(this)
        //URL for the coin API
        var url = "https://api.coincap.io/v2/assets"

        //Call to API
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                //Create a JSON Array from the JSON object returned
                var dataArray : JSONArray = response.getJSONArray("data")

                //TESTING: Loop thru and list elements of the JSONArray
                for(i in 0 until dataArray.length()) {
                    //${} is to interpolate the string /
                    // uses a string template
                    var dataJsonObject: JSONObject = dataArray.getJSONObject(i)
                    //now get the properties we want: name and description
                    Log.i("MainActivity", "ID: ${dataJsonObject.getString("id")}")
                    Log.i("MainActivity", "Name: ${dataJsonObject.getString("name")}")
                }//end JSONArray

            },
            Response.ErrorListener { error ->
                Log.i("MainActivity", "That didn't work!")

            }
        )//end call to API

        //Add to requestQueue
        requestQueue.add(jsonObjectRequest)
    }


} //End MainActivity