package com.esaldivia.urbandictionary.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaldivia.urbandictionary.R
import com.esaldivia.urbandictionary.models.Word
import com.esaldivia.urbandictionary.network.Status
import com.esaldivia.urbandictionary.viewmodels.DictionaryViewModel
import com.esaldivia.urbandictionary.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_dicitionary.*
import javax.inject.Inject

class DicitionaryActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    val viewModel by viewModels<DictionaryViewModel> { providerFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicitionary)

        setupSearchView()
        setupRecyclerView()
    }

    fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    setupRecyclerView()
                }
                return true
            }
        })

    }

    fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(baseContext)

            adapter = WordListAdapter(arrayListOf())
        }
    }

    fun setupObservers(word: String) {
        viewModel.searchTerm(word).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        resource.data?.let { response -> retrieveList(response.definitions) }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }

            }
        })
    }

    fun search(word: String) {
        setupObservers(word)
        recyclerView.apply {
            val wordListAdapter = adapter as WordListAdapter
            wordListAdapter.clearItems()
            wordListAdapter.notifyDataSetChanged()
        }
    }

    fun retrieveList(words: ArrayList<Word>) {
        recyclerView.apply {
            adapter = WordListAdapter(words)
            (adapter as WordListAdapter).notifyDataSetChanged()
        }
    }
}