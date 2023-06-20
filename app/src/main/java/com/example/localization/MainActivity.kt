package com.example.localization

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.localization.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        editor = sharedPreferences.edit()
         val selectedLanguage =sharedPreferences.getString ("locale","en")
        dataShow(selectedLanguage)

        when(selectedLanguage){
            "en"-> binding.btnRadioEnglish.isChecked = true
            else-> binding.btnRadioHindi.isChecked = true
        }
        binding.btnRadioHindi.setOnClickListener{
            editor.putString("locale","hi")
            editor.apply()
        }
        binding.btnRadioEnglish.setOnClickListener{
            editor.putString("locale","en")
            editor.apply()
        }
        binding.btntextchange.setOnClickListener {
            startActivity(Intent(this,SplashActivity::class.java))

        }
    }

    private fun dataShow(selectedLanguage: String?) {
        val locale =Locale(selectedLanguage)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        createConfigurationContext(config)
    }
}