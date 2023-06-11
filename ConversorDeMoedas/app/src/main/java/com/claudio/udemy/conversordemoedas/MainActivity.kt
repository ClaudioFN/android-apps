@file:OptIn(ExperimentalMaterial3Api::class)

package com.claudio.udemy.conversordemoedas

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.claudio.udemy.conversordemoedas.ui.theme.ConversorDeMoedasTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://economia.awesomeapi.com.br/"
var valorDiaEuro = getMyData("USD-BRL");
var valorDiaDolar = getMyData("EUR-BRL");
// SITE https://docs.awesomeapi.com.br/api-de-moedas
/**
 * @author: Made by Claudio F. N.
 *
 * This class executes the main properties of the app
 * Inherit from ComponentActivity
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                ConversorDeMoedasTheme {
                    // App owner
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.secondary
                    ) {
                        Greeting("Claudio")
                        CenterMessage("Qual o valor em real(is)?")
                    }

                    Surface(
                        modifier = Modifier
                            .padding(100.dp, 195.dp)
                            .fillMaxWidth()
                    ) {
                        // Type the value to be converted
                        // Gets the API API DE MOEDAS to search for the Dollar and Euro values of the day
                        // Convert the value
                        // Show the value converted and the value of the coin in the day
                        ValueTyped()
                    }
                }
            }
        }
}

/**
 * Function getMyData
 * @param idMoeda of the type String: is the identification of the coin (USD-BRL -> DOLLAR | EUR-BRL -> EURO)
 * @return It will return the value in Double format of the day according to the selected coin
 */
private fun getMyData(idMoeda: String): Double {
    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(APIInterface::class.java)
    // Teste to confirm which coin will be searched
    // First Dollar
    // Second Euro
    if (idMoeda.equals("USD-BRL")){
        val retrofitData = retrofitBuilder.getDataUSD(idMoeda)
        retrofitData.enqueue(object : Callback<ResultUSD> {
            override fun onResponse(call: Call<ResultUSD>?, response: Response<ResultUSD>?) {
                val responseBody = response?.body()!!
                Log.d("Message 1.1-->> ", response.body().toString())
                valorDiaDolar = responseBody.USDBRL.low.toDouble()
                Log.d("Message 2.1-->> ", valorDiaDolar.toString())
            }

            override fun onFailure(call: Call<ResultUSD>, t: Throwable) {
                Log.d(TAG, "getMyData-OnFailure: " + t.message)

            }
        }
        )
    } else {
        val retrofitData = retrofitBuilder.getDataEUR(idMoeda)
        retrofitData.enqueue(object : Callback<ResultEUR> {
            override fun onResponse(call: Call<ResultEUR>?, response: Response<ResultEUR>?) {
                val responseBody = response?.body()!!
                Log.d("Message 1.2-->> ", response.body().toString())
                valorDiaEuro = responseBody.EURBRL.low.toDouble()
                Log.d("Message 2.2-->> ", valorDiaEuro.toString())
            }

            override fun onFailure(call: Call<ResultEUR>, t: Throwable) {
                Log.d(TAG, "getMyData-OnFailure: " + t.message)

            }
        }
        )
    }
    var retValue = 0.0
    if (idMoeda.equals("USD-BRL")){
        retValue = valorDiaDolar
    } else {
        retValue = valorDiaEuro
    }

    return retValue
}

@Composable
fun Greeting(name: String) {
    Surface {
        Text(text = "Hi, my name is $name!", textAlign = TextAlign.Center)
    }
}
/**
 * Function CenterMessage - It will return the box with the received text
 * @param textInCenter of the type String: text to be shown in the designated area
 * @param modifier of the type Modifier: already initiated with the value Modifier to allow configurations of
 * position, size, color of the text, font size
 */
@Composable
fun CenterMessage(textInCenter: String, modifier: Modifier = Modifier){
    Text(text = textInCenter,
        modifier
            .padding(100.dp, 100.dp)
            .width(180.dp), Color.DarkGray, fontSize = 30.sp, textAlign = TextAlign.Center )
}
/**
 * Function ValueTyped - It will return the box to be used as a typing place to the values to be converted. It also do the conversion.
 */
@Composable
fun ValueTyped(){
    var text by remember { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }

    /**
     * Function validate - It will address a Boolean value to the variable isError: TRUE if the text has letters and FALSE
     * if the text does not have letters
     * @param text of the type of String: text that was typed on the box created by ValueTyped
     */
    fun validate(text: String) {
        isError = text.contains(regex = Regex("[a-zA-Z]"))
    }

    Log.d("Message 4-->> ", valorDiaDolar.toString())
    OutlinedTextField(
        value = text, // Text typed
        onValueChange = { text = it; validate(text);}, // Call of the validation function
        label = { Text("Digite o valor", textAlign = TextAlign.Center) }, // Place holder
        isError = isError,
        supportingText = { // If the text has letters, return message of error. If not, show the values converted
            if (isError) {
                Text(
                    text = "Valor ${text} digitado tem letras!", fontSize = 10.sp, textAlign = TextAlign.Center
                )
            } else {
                // Lowest value of Dollar on the day
                Text(text = "Valor do dólar hoje: $valorDiaDolar",
                    Modifier
                        .padding(10.dp, 50.dp)
                        .width(350.dp), Color.Gray, fontSize = 21.sp, textAlign = TextAlign.Left )
                // Convert the value typed from Real to Dollar
                var dolar = 0.0
                Log.d("Message 5-->> ", valorDiaDolar.toString())
                if (text.isNotEmpty()){
                    dolar = text.toDouble() / ( Math.round(valorDiaDolar * 100.0) / 100.0)
                }
                // Round the value to only 2 decimal
                dolar = ( Math.round(dolar * 100.0) / 100.0)
                // Show the value converted
                Text(text = "Valor em dólar: $dolar",
                    Modifier
                        .padding(10.dp, 100.dp)
                        .width(350.dp), Color("#A4D0A4".toColorInt()), fontSize = 25.sp, textAlign = TextAlign.Left )
                // Lowest value of Euro on the day
                Text(text = "Valor do euro hoje: $valorDiaEuro",
                    Modifier
                        .padding(10.dp, 150.dp)
                        .width(400.dp), Color.Gray, fontSize = 21.sp, textAlign = TextAlign.Left )
                //Convert the value typed from Real to Euro
                var euro = 0.0
                Log.d("Message 6-->> ", valorDiaEuro.toString())
                if (text.isNotEmpty()){
                    euro = text.toDouble() / ( Math.round(valorDiaEuro * 100.0) / 100.0)
                }
                // Round the value to only 2 decimal
                euro = ( Math.round(euro * 100.0) / 100.0)
                // Show the value converted
                Text(text = "Valor em euro: $euro",
                    Modifier
                        .padding(10.dp, 200.dp)
                        .width(400.dp), Color("#F9D949".toColorInt()), fontSize = 25.sp, textAlign = TextAlign.Left )
            }
        }
    )
}

/**
 * Function GeneralPreview - It will show a preview during the execution on the Android Studio. It calls all available functions
 */
@Preview(showBackground = true)
@Composable
fun GeneralPreview() {
    ConversorDeMoedasTheme {
        Greeting("Claudio!")
        CenterMessage("Qual o valor a ser convertido")
        ValueTyped()
    }
}