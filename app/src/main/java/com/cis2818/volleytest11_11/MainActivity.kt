package com.cis2818.volleytest11_11


import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.SECONDS


class MainActivity : AppCompatActivity() {

    //Class Data
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: CoinViewModel
    private lateinit var rawData : JSONArray
    private lateinit var test : String

    /**
     * Function: onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //Set view model
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        //Set binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get the coin data from API and save to the viewModel
        //Populate the spinner dropdown
        //Populate initial record
         fetchAndSetCoinData()





        var languages = arrayOf("Java", "PHP", "Kotlin", "Javascript", "Python", "Swift")

        val spinner: Spinner = findViewById(R.id.spCoinChoice)

       // binding.spCoinChoice. --> View Binding?

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }

/*
        binding.spCoinChoice.setOnClickListener{
           Log.i("MainActivity","Spinner Clicked")
        }
*/


    }//end fun OnCreate


    /**
     * FUNCTION: fetchCoinData
     */
    private fun fetchAndSetCoinData()  {

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

                   viewModel.setCoinData(dataArray)


                   var currObject = viewModel.getACurrency(2)

                   Log.i("MainActivity","Result: ${currObject.currName}")
                   Log.i("MainActivity","Result: ${currObject.supply}")

                    Log.i("MainActivity","Length: ${viewModel.getLength()}")


                 },
            Response.ErrorListener { error ->
                Log.i("MainActivity", "That didn't work!")

            }
        )//end call to API

        //Add to requestQueue
        requestQueue.add(jsonObjectRequest)


    } //end fetchCoinData

/*
    //Get A capital
    binding.capitalInfo.setText(viewModel.getCapital())

    /**
     * OnClickListener: Function
     */
    binding.nextButton.setOnClickListener {
        //Get a capital
        binding.capitalInfo.setText(viewModel.getCapital())
    }//end setOnClickListener
*/

} //End MainActivity