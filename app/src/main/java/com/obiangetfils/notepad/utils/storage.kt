package com.obiangetfils.notepad.utils

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.obiangetfils.notepad.model.Note
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

private val TAG = "storage"

fun persistNote(context : Context, note : Note) {
    if (TextUtils.isEmpty(note.filename)){
        note.filename = UUID.randomUUID().toString()+".note"
    }
    val fileOutPut = context.openFileOutput(note.filename, Context.MODE_PRIVATE)
    val outPutStream = ObjectOutputStream(fileOutPut)
    outPutStream.writeObject(note)
    outPutStream.close()
}

private fun loadNote(context : Context, filename : String) : Note {
    val fileInPut = context.openFileInput(filename)
    val inputStream = ObjectInputStream(fileInPut)
    val note = inputStream.readObject() as Note
    inputStream.close()
    return note
}

fun deleteNote(context: Context, note: Note){
    context.deleteFile(note.filename)
}

fun loadNotes(context: Context) : MutableList<Note> {
    val notes = mutableListOf<Note>()
    val notesDir = context.filesDir
    for (filename in notesDir.list()){
        val note = loadNote(context, filename)
        Log.i(TAG, "Loaded note $note")
        notes.add(note)
    }
    return notes
}