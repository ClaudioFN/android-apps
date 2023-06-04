@file:OptIn(ExperimentalMaterial3Api::class)

package com.claudio.udemy.conversordemoedas

import android.content.ContentValues.TAG
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.claudio.udemy.conversordemoedas.ui.theme.ConversorDeMoedasTheme
import android.util.DisplayMetrics
import android.util.Log
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

val displayMetrics = DisplayMetrics()
val errorMessage = "Text input too long"

const val BASE_URL = "https://economia.awesomeapi.com.br/last/"
var valorDiaEuro = 0.0;
var valorDiaDolar = 0.0;
// SITE https://docs.awesomeapi.com.br/api-de-moedas
// ORIGEM https://stackoverflow.com/questions/73265113/how-to-make-api-call-in-kotlin-using-retrofit-android-with-nested-json-apis
// PESQUISA kotlin api call
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

                // Type the value to be converted
                Surface(
                    modifier = Modifier
                        .padding(100.dp, 180.dp)
                        .fillMaxWidth()
                ) {
                    ValueTyped()
                }
            }
        }
        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL+"USD-BRL") // EUR-BRL  USD-BRL
            .build()
            .create(APIInterface::class.java)
        Log.d("Inside getMyData", "--")
        val retrofitData = retrofitBuilder.getData()
        Log.d("Inside getMyData", "--")
        retrofitData.enqueue(object : Callback<Status> {
            override fun onResponse(call: Call<Status>, response: Response<Status>) {
                val responseBody = response.body()!!
                Log.d("Message------------------------ ", responseBody.Message)
                Log.d("RecordCount ", responseBody.RecordCount)
                Log.d("Status ", responseBody.Status.toString())

                for (i in 0 until responseBody.Result.size) {
                    Log.d("code------------------------ ", responseBody.Result[i].code)
                    Log.d("codein ", responseBody.Result[i].codein)
                    Log.d("name ", responseBody.Result[i].name)
                    Log.d("high ", responseBody.Result[i].high)
                    Log.d("low ", responseBody.Result[i].low)
                    Log.d("varBid ", responseBody.Result[i].varBid)
                    Log.d("pctChange ", responseBody.Result[i].pctChange)
                    Log.d("bid ", responseBody.Result[i].bid)
                    Log.d("ask ", responseBody.Result[i].ask)
                    Log.d("timestamp ", responseBody.Result[i].timestamp)
                    Log.d("create_date ", responseBody.Result[i].create_date)
                }
            }

            override fun onFailure(call: Call<Status>, t: Throwable) {
                Log.d(TAG, "OnFailure: " + t.message)

            }
        }
        )
    }

}

@Composable
fun Greeting(name: String) {
    Surface {
        Text(text = "Hi, my name is $name!", textAlign = TextAlign.Center)
    }
}

@Composable
fun CenterMessage(textInCenter: String, modifier: Modifier = Modifier){
    Text(text = textInCenter,
        modifier
            .padding(100.dp, 100.dp)
            .width(180.dp), Color.DarkGray, fontSize = 30.sp, textAlign = TextAlign.Center )
}

@Composable
fun ValueTyped(){
    var text by remember { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    //var valueConvertedEuro by remember { mutableStateOf("") }
    //var valueConvertedDolar by remember { mutableStateOf("") }


    fun validate(text: String) {
        isError = text.contains(regex = Regex("[a-zA-Z]"))
    }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it; validate(text)},
        label = { Text("Digite o valor", textAlign = TextAlign.Center) },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = "Valor ${text} digitado tem letras!", fontSize = 10.sp, textAlign = TextAlign.Center
                )
            } else {
                var dolar = 0.0
                if (text.isNotEmpty()){
                    dolar = text.toDouble() * 2
                }
                Text(text = "Valor em Dólar: $dolar",
                    Modifier
                        .padding(10.dp, 100.dp)
                        .width(350.dp), Color.Green, fontSize = 25.sp, textAlign = TextAlign.Left )
                var euro = 0.0
                if (text.isNotEmpty()){
                    euro = text.toDouble() * 2
                }
                Text(text = "Valor em Euro: $euro",
                    Modifier
                        .padding(10.dp, 200.dp)
                        .width(400.dp), Color.Yellow, fontSize = 25.sp, textAlign = TextAlign.Left )
            }
        }
    )
}
/*
@Composable
fun TextFieldText(textShow: String, horizontal: Int, valueToConvert: MySharedViewModel? = null, modifier: Modifier = Modifier){

    Text(text = textShow,
        modifier
            .padding(10.dp, horizontal.dp)
            .width(280.dp), Color.DarkGray, fontSize = 30.sp, textAlign = TextAlign.Center )
}
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