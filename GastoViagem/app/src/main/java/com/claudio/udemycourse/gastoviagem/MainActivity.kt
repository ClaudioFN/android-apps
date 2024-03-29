package com.claudio.udemycourse.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.claudio.udemycourse.gastoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Initiate
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate){
            calculate()
        }
    }

    private fun isValid(): Boolean{
        return (   binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAuthonomy.text.toString() != ""
                && binding.editAuthonomy.text.toString().toFloat() != 0f)
    }

    private fun calculate(){
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val authonomy = binding.editAuthonomy.text.toString().toFloat()

            val totalValue = (distance * price) / authonomy
            binding.textTotalValue.text = "R$${"%.2f".format(totalValue)}"
        } else {
            Toast.makeText(this, R.string.validate_all_fields, Toast.LENGTH_SHORT).show()
        }

    }


}