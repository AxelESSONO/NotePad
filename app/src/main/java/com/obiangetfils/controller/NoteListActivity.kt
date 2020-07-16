package com.obiangetfils.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.obiangetfils.adapter.NoteAdapter
import com.obiangetfils.notepad.R
import com.obiangetfils.notepad.model.Note

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes: MutableList<Note>
    lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        findViewById<FloatingActionButton>(R.id.create_note_fab).setOnClickListener(this)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode != Activity.RESULT_OK || data == null) {
            return
        }
        when (requestCode) {
            NoteDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)
        val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)
        saveNote(note, noteIndex)
    }

    private fun saveNote(note: Note, noteIndex: Int) {

        if (noteIndex < 0) {
            notes.add(0, note)
        } else {
            notes[noteIndex] = note
        }
        adapter.notifyDataSetChanged()
    }

    fun showNoteDetail(noteIndex: Int) {
        val note = if (noteIndex < 0) Note() else notes[noteIndex]
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, noteIndex)
        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)
    }

    private fun createNewNote() {
        showNoteDetail(-1)
    }

    override fun onClick(view: View) {

        if (view.tag != null) {
            showNoteDetail(view.tag as Int)
        } else {
            when (view.id) {
                R.id.create_note_fab -> createNewNote()
            }
        }
    }

}