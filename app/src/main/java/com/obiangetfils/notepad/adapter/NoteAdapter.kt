package com.obiangetfils.notepad.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.obiangetfils.notepad.R
import com.obiangetfils.notepad.model.Note

class NoteAdapter(val notes : List<Note>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    class NoteHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val cardView = itemView.findViewById<CardView>(R.id.card_view_note)
        val titleView = cardView.findViewById<TextView>(R.id.title)
        val exerptView = cardView.findViewById<TextView>(R.id.excerpt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteHolder(viewItem)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.titleView.text = note.title
        holder.exerptView.text = note.text
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}