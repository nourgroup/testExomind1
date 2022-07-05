package ft.exomind.testexomind.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ft.exomind.testexomind.R

import ft.exomind.testexomind.data.model.City
import ft.exomind.testexomind.databinding.ActivityMainBinding
import ft.exomind.testexomind.viewmodels.ViewModelProgressBar

class MainActivity : AppCompatActivity() {
    lateinit var _binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}