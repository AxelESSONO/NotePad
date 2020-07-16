package com.obiangetfils.notepad.utils

import android.content.Context
import android.text.TextUtils
import com.obiangetfils.notepad.model.Note
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