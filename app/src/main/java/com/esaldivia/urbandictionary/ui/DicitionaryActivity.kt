package com.esaldivia.urbandictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.esaldivia.urbandictionary.R
import com.esaldivia.urbandictionary.viewmodels.DictionaryViewModel
import com.esaldivia.urbandictionary.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class DicitionaryActivity : AppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    val viewModel by viewModels<DictionaryViewModel> { providerFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicitionary)
    }
}