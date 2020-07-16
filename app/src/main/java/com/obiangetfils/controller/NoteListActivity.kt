package com.obiangetfils.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.obiangetfils.adapter.NoteAdapter
import com.obiangetfils.notepad.R
import com.obiangetfils.notepad.model.Note

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes : MutableList<Note>
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Load List
        notes = mutableListOf<Note>()
        notes.add(Note("Note 1", "Blablabla"))
        notes.add(Note("Mémo Axel", "Futur grand développeur Android"))
        notes.add(Note("Mémo Cédric", "Je serai indépendant"))
        notes.add(Note("Travail à faire!", "Blablabla"))

        // configure RecyclerView
        adapter = NoteAdapter(notes, this)
        val recyclerView = findViewById<RecyclerView>(R.id.note_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onClick(view: View) {

        if (view.tag != null){
            showNoteDetail(view.tag as Int)
        }
    }

    fun showNoteDetail(noteIndex : Int){
        val note = notes[noteIndex]
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        startActivity(intent)
    }

}