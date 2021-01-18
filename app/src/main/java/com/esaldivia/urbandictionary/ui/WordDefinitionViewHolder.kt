package com.esaldivia.urbandictionary.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.esaldivia.urbandictionary.R

class WordDefinitionViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_word, parent, false)) {

    var rootView: View = itemView.findViewById(R.id.list_word)

    var wordTextView: TextView = itemView.findViewById(R.id.word_text_view)

    var definitionTextView: TextView = itemView.findViewById(R.id.definition_text_view)

    var authorTextView: TextView = itemView.findViewById(R.id.author_text_view)

    var thumbsUpTextView: TextView = itemView.findViewById(R.id.thumbs_up_text_view)

    var thumbsDownTextView: TextView = itemView.findViewById(R.id.thumbs_down_text_view)

}