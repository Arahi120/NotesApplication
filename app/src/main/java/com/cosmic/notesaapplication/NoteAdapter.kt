package com.cosmic.notesaapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var mnotes: List<Notes>) : RecyclerView.Adapter<NoteAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): NotesViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.item_notes, parent, false)
        return  NotesViewHolder(view)

    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = mnotes[position]
        holder.render(note)
    }

    override fun getItemCount(): Int {
        return mnotes.size
    }

    class NotesViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val name: TextView = view.findViewById(R.id.device_name_content)
        val date: TextView = view.findViewById(R.id.ip_address_content)
        val content: TextView = view.findViewById(R.id.mac_address_content)


        fun render(note: Notes) {
            name.text = note.title
            date.text = note.date
            content.text = note.content
        }

    }

}