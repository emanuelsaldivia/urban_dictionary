package com.esaldivia.urbandictionary.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
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

class DictionaryActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    private val viewModel by viewModels<DictionaryViewModel> { providerFactory }
    private var definitions: ArrayList<Word> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dicitionary)

        setupSearchView()
        setupRecyclerView()
        setupDictionaryFilters()
    }

    private fun setupDictionaryFilters() {
        filterLayout.setOnClickListener {
            if (viewModel.isOrderedByThumbsUp.value == true) {
                viewModel.orderByThumbsDown(definitions)
            } else {
                viewModel.orderByThumbsUp(definitions)
            }
        }
    }

    fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // After clearing the searchView, the results clear too
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

    private fun setupObservers(word: String) {
        viewModel.searchTerm(word).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        resource.data?.let { response ->
                            definitions = response.definitions
                            viewModel.orderByThumbsUp(definitions)
                            retrieveList(definitions)
                        }
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

        viewModel.isOrderedByThumbsUp.observe(this, Observer {
            if (viewModel.isOrderedByThumbsUp.value == true) {
                votesOrder.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_thumb_up_24))
            } else {
                votesOrder.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_thumb_down_24))
            }
        })

        viewModel.definitionsOrder.observe(this, Observer {
            retrieveList(it!!)
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

    private fun retrieveList(words: ArrayList<Word>) {
        recyclerView.apply {
            adapter = WordListAdapter(words)
            (adapter as WordListAdapter).notifyDataSetChanged()
        }
    }
}