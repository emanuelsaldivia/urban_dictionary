package com.esaldivia.urbandictionary.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esaldivia.urbandictionary.models.Word

class WordListAdapter(private val wordList: ArrayList<Word>) : RecyclerView.Adapter<WordDefinitionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordDefinitionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WordDefinitionViewHolder(layoutInflater, parent)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordDefinitionViewHolder, position: Int) {
        val item = wordList[position]
        setupViewHolder(item, holder)
    }

    private fun setupViewHolder(word: Word, holder: WordDefinitionViewHolder) {
        holder.wordTextView.text = word.word

        holder.definitionTextView.text = word.definition

        holder.authorTextView.text = word.author

        holder.thumbsUpTextView.text = word.thumbsUp.toString()

        holder.thumbsDownTextView.text = word.thumbsDown.toString()

    }

}