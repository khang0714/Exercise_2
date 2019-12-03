package com.example.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    lateinit var buttonReset: Button
    lateinit var editTextWeight: EditText
    lateinit var editTextHeight:EditText
    lateinit var imageViewProfile: ImageView
    lateinit var textViewBMI: TextView
    lateinit var buttonCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonReset = findViewById(R.id.buttonReset)
        editTextWeight = findViewById(R.id.editTextWeight)
        editTextHeight = findViewById(R.id.editTextHeight)
        imageViewProfile = findViewById(R.id.imageViewProfile)
        textViewBMI = findViewById(R.id.textViewBMI)
        buttonCalculate=findViewById(R.id.buttonCalculate)

        buttonCalculate.setOnClickListener{ calculate() }
        buttonReset.setOnClickListener{ reset() }
    }

    private fun calculate(){
        //Calculate BMI
        var weight = editTextWeight.text.toString().toDouble()
        var height = editTextHeight.text.toString().toDouble() / 100
        var bmi = (weight / (Math.pow(height, 2.0))).toBigDecimal().setScale(1, BigDecimal.ROUND_HALF_UP).toDouble()
        textViewBMI.text = textViewBMI.text.toString() + bmi.toString()

        when {
            bmi < 18.5 -> imageViewProfile.setImageDrawable(resources.getDrawable(R.drawable.under))
            bmi in 18.5..24.9 ->imageViewProfile.setImageDrawable(resources.getDrawable(R.drawable.normal))
            else -> imageViewProfile.setImageDrawable(resources.getDrawable(R.drawable.over))
        }
    }

    private fun reset(){
        editTextWeight.text = null
        editTextHeight.text = null
        textViewBMI.text = resources.getString(R.string.bmi)
        imageViewProfile.setImageDrawable(resources.getDrawable(R.drawable.empty))
    }
}
