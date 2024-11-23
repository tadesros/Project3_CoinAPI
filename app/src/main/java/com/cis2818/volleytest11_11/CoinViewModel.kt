package com.cis2818.volleytest11_11
import android.icu.text.DecimalFormat
import android.util.Log
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.roundToInt

/** CLASS: CoinViewModel **/
class CoinViewModel : ViewModel() {

    private lateinit var currList : ArrayList<Currency>

       /**
        * Function: setCoinData
       */
       fun setCoinData(inputJArray : JSONArray)
       {
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
            val dataJsonObject: JSONObject = inputJArray.getJSONObject(i)

            //Fix Supply
            val stringSupply =  dataJsonObject.getString("supply")
            val floatValue = stringSupply.toFloat()
            val adjFloat = floatValue.roundToInt()
            val finalStringSupply = adjFloat.toString()

            //Fix Price
            val stringPrice =  dataJsonObject.getString("priceUsd")
            val floatPrice = stringPrice.toFloat()
            val decimalFormat = DecimalFormat("#.##")
            val adjFloatPrice = decimalFormat.format(floatPrice)
            var roundedPrice = adjFloatPrice.toString()
            roundedPrice = "$ " + roundedPrice

            //Fix Percentage
            val stringPerc =  dataJsonObject.getString("changePercent24Hr")
            val floatPerc = stringPerc.toFloat()
            val adjFloatPerc = decimalFormat.format(floatPerc)
            var roundedPerc = adjFloatPerc.toString()
            roundedPerc =  roundedPerc + " %"

            //Set values
            currName = dataJsonObject.getString("name")
            symbol = dataJsonObject.getString("symbol")
            supply = finalStringSupply
            price = roundedPrice
            percChange = roundedPerc

            //Create Instance of Currency
            currency = Currency(currName, symbol, supply, price, percChange)
            //Add to ArrayList
            currList.add(currency)

        }//end for loop

    }//end Function: setCoinData

        /**
         * Function: getACurrency()
         */
        fun getACurrency(index : Int): Currency
        {
            // Creating a copy
            val currObj: Currency
            val currObjReturn : Currency

            currObj = currList.get(index)

            //Set values
            val currName = currObj.currName
            val symbol = currObj.symbol
            val supply = currObj.supply
            val price = currObj.price
            val percChange = currObj.percChange

            //Create Instance of Currency
            currObjReturn = Currency(currName, symbol, supply, price, percChange)

            return currObjReturn
        }//end Function: getACurrency

       /**
     * Function: getLength()  Return the length of the objects
     */
       fun getLength(): Int
       {
            val length = currList.size
            return length
    }//end function: getLength

}//end class CoinViewModel