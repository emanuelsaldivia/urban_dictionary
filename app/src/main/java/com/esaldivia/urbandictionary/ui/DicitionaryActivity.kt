package com.esaldivia.urbandictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaldivia.urbandictionary.R
import com.esaldivia.urbandictionary.models.Word
import com.esaldivia.urbandictionary.viewmodels.DictionaryViewModel
import com.esaldivia.urbandictionary.viewmodels.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_dicitionary.*
import javax.inject.Inject

class DicitionaryActivity : AppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    val viewModel by viewModels<DictionaryViewModel> { providerFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicitionary)

        setupRecyclerView()
    }

    fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(baseContext)

            adapter = WordListAdapter(
                    arrayListOf(Word(1, "word1", "definition1", "author1", 10, 10),
                    Word(2, "word2", "definition2", "author2", 10, 10),
                    Word(3, "word3", "definition3", "author3", 10, 10),
                    Word(4, "word4", "definition4", "author4", 10, 10),
                    Word(5, "word5", "definition5", "author5", 10, 10),
                    Word(6, "word6", "definition6", "author6", 10, 10),
                    Word(7, "word7", "definition7", "author7", 10, 10),
                    Word(8, "word8", "definition8", "author8", 10, 10),
                    Word(9, "word9", "definition9", "author9", 10, 10)))
        }
    }

    fun setupObservers() {
        viewModel.wordDefinitionsLiveData.observe(this, Observer {
            it?.let {
                retrieveList(it)
            }
        })
    }

    fun retrieveList(words: ArrayList<Word>) {
        recyclerView.apply {
            adapter = WordListAdapter(words)
            (adapter as WordListAdapter).notifyDataSetChanged()
        }
    }
}