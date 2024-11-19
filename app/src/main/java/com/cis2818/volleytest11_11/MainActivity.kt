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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //set button handler
        binding.btnGetCatData.setOnClickListener {
          //  printCatData()

            printCoinAPI()

            Log.i("MainActivity","After Click Event")

        } //call this other function }

    }//end fun OnCreate

    // method to interact with API
    private fun printCatData() {

            var catUrl = "https://api.thecatapi.com/v1/breeds" + "?api_key=live_2QDLowrYlSx01usbDxyfErqHaYikqHOA4XM7xZiXAY9OUloxoh11aiqy7cY6UACA"

            val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
               Request.Method.GET, catUrl,
               Response.Listener<String> { response ->
                   var catsArray : JSONArray = JSONArray(response)

               //indices from 0 through catsArray.length()-1
                for(i in 0 until catsArray.length()) {
                //${} is to interpolate the string /
                // uses a string template
                 var theCat : JSONObject = catsArray.getJSONObject(i)
                //now get the properties we want: name and description
                    Log.i("MainActivity", "Cat name: ${theCat.getString("name")}")
                    Log.i("MainActivity", "Cat description: ${theCat.getString("description")}")
                }//end for
                },
                   Response.ErrorListener {
                       Log.i("MainActivity", "That didn't work!")
                   })
                    // Add the request to the RequestQueue.
                     queue.add(stringRequest)
            }//end printCatData

    // method to interact with API for COIN API
    private fun printCoinAPI() {

        var coinUrl = "https://api.coincap.io/v2/assets"

        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, coinUrl,
            Response.Listener<String> { response ->
                var coinArray : JSONArray = JSONArray(response)


                //indices from 0 through catsArray.length()-1
/*

                for(i in 0 until coinArray.length()) {
                    //${} is to interpolate the string /
                    // uses a string template

                    Log.i("MainActivity", "In for loop")
                  //  var theCoin : JSONObject = coinArray.getJSONObject(i)
                    //now get the properties we want: name and description
                 //   Log.i("MainActivity", "Cat name: ${theCoin.getString("name")}")
                  //  Log.i("MainActivity", "Cat description: ${theCoin.getString("symbol")}")
                }//end for
*/

            },
            Response.ErrorListener {
                Log.i("MainActivity", "That didn't work!")
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }//end printCoinAPI






} //End MainActivity