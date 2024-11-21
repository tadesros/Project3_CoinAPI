package com.cis2818.volleytest11_11
import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random


class CoinViewModel : ViewModel() {

    private lateinit var currList : ArrayList<Currency>

       /**
     * Function: setCoinData
     */
       fun setCoinData(inputJArray : JSONArray) {


        // Creating a copy
        var currName: String
        var symbol: String
        var supply: String
        var price: String
        var percChange: String
        var currency: Currency

        currList = ArrayList<Currency>()


        for (i in 0 until inputJArray.length()) {


            //Get the current JSONObject
            var dataJsonObject: JSONObject = inputJArray.getJSONObject(i)

            //Set values
            currName = dataJsonObject.getString("name")
            symbol = dataJsonObject.getString("symbol")
            supply = dataJsonObject.getString("supply")
            price = dataJsonObject.getString("priceUsd")
            percChange = dataJsonObject.getString("changePercent24Hr")

            //Create Instance of Currency
            currency = Currency(currName, symbol, supply, price, percChange)
            //Add to ArrayList
            currList.add(currency)

        }//end for loop


    }//end setCoinData

        /**
         * Function: getACurrency()
         */
        fun getACurrency(index : Int): Currency
        {
            // Creating a copy
            var currObj: Currency
            var currObjReturn : Currency

            currObj = currList.get(index)

            //Set values
            var currName = currObj.currName
            var symbol = currObj.symbol
            var supply = currObj.supply
            var price = currObj.price
            var percChange = currObj.percChange

            //Create Instance of Currency
            currObjReturn = Currency(currName, symbol, supply, price, percChange)

            return currObjReturn
        }//end getACurrency


       /**
     * Function: getLength()  Return the length of the objects
     */
       fun getLength(): Int {
            val length = currList.size
            return length
    }//end getLength


}//end class ConViewModel