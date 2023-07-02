package com.claudio.udemycourse.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.claudio.udemycourse.motivation.infra.MotivationConstants
import com.claudio.udemycourse.motivation.R
import com.claudio.udemycourse.motivation.data.Mock
import com.claudio.udemycourse.motivation.infra.SecurityPreferences
import com.claudio.udemycourse.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)

        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUser.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase){
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }else if (view.id == R.id.text_user) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        if (id == R.id.image_all) {
            binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.ALL
        } else if (id == R.id.image_happy) {
            binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.HAPPY
        } else if (id == R.id.image_sunny) {
            binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.SUNNY
        }
    }

    fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUser.text = "Olá, $name"
    }
}