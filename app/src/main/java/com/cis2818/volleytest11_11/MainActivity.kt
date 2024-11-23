package com.cis2818.volleytest11_11

//import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.cis2818.volleytest11_11.databinding.ActivityMainBinding
import org.json.JSONArray


/** CLASS: MAIN ACTIVITY **/
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    //Data
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: CoinViewModel
    private var dropDownChoices = arrayOf<String>()

    /**
     * FUNCTION: onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)

        //Set view model
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        //Set binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Get the coin data from API and save to the viewModel
         * Populate the spinner dropdown
         * Populate initial record
        **/
         fetchAndSetCoinData()

    }//end function -  OnCreate

    /**
     * FUNCTION: fetchCoinData
     */
    private fun fetchAndSetCoinData()
    {

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

                //Set ViewModel
                viewModel.setCoinData(dataArray)

                //Populate dropdown choice Array
                populateDropDownChoiceArray()

                //Set Up Spinner
                setUpSpinner()

                //Populate Initial View
                setInitialView()

                 },
            Response.ErrorListener { error ->
                Log.i("MainActivity", "That didn't work!")
            }
        )//end call to API

        //Add to requestQueue
        requestQueue.add(jsonObjectRequest)

    } //end function - fetchCoinData

    /**
     * FUNCTION: onItemSelected
     */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        val intPosition = parent?.getItemIdAtPosition(position)

        if (intPosition != null) {
            updateView(intPosition)
        }
    }// end function - onItemSelected

    /**
     * FUNCTION: onNothingSelected
     */
    override fun onNothingSelected(parent: AdapterView<*>?)
    {
        Log.i("Main Activity", "On Nothing Item Selected Clicked")
    }//end function - onNothingSelected

    /**
     *  FUNCTION: populateDropDownChoiceArray - Populate Dropdown Choice Array
     */
    private fun populateDropDownChoiceArray()
    {
        //Get Length
         val lengthItems = viewModel.getLength()
         val namesList = dropDownChoices.toMutableList()
        //Loop thru each one
        for(i in 0 until lengthItems)
        {
            val currObject = viewModel.getACurrency(i)

           // Log.i("MainActivity","Name: ${currObject.currName} and ${i} ")
            namesList.add(currObject.currName)
        }
        dropDownChoices = namesList.toTypedArray()
    }//end function - populateDropdownChoices

   /**
    * FUNCTION: setUpSpinner - Bind Spinner dropdown
    */
   private fun setUpSpinner()
   {
       //Bind Spinner object
       val spinner: Spinner = findViewById(R.id.spCoinChoice)

       val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
           this,
           androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,  dropDownChoices
       )
       spinner.setAdapter(arrayAdapter)
       spinner.onItemSelectedListener = this
    }//end function - setUpSpinner

   /**
    * FUNCTION: setInitialView
    */
   private fun setInitialView()
   {
      //Get first record
       val currObject = viewModel.getACurrency(0)
       //Get A capital
       binding.txtName.text = currObject.currName
       binding.txtSymbol.text = currObject.symbol
       binding.txtSupply.text = currObject.supply
       binding.txtPrice.text = currObject.price
       binding.txtPercChange.text = currObject.percChange
    }//end function - populateInitialView

  /** FUNCTION: updateView **/
  private fun updateView(itemNumber: Long)
  {
      val intValue: Int = itemNumber.toInt()
      //Get first record
      val currObject = viewModel.getACurrency(intValue)

      binding.txtName.text = currObject.currName
      binding.txtSymbol.text = currObject.symbol
      binding.txtSupply.text = currObject.supply
      binding.txtPrice.text = currObject.price
      binding.txtPercChange.text = currObject.percChange

  }//end function - updateView

}//end CLASS: MainActivity


