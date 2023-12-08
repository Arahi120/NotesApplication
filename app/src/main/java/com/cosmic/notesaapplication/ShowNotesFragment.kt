package com.cosmic.notesaapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShowNotesFragment : Fragment(), AddNoteBottomSheetFragment.OnAddNoteListener {

    private var notes: MutableList<Notes> = mutableListOf()
    private lateinit var adapter: NoteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_show_notes, container, false)
        val buttonDialog = view.findViewById<FloatingActionButton>(R.id.fab_add_note)

        initData()

        val recyclerView = view.findViewById<RecyclerView>(R.id.noteRecycler)
        adapter = NoteAdapter(notes)
        val layoutManager = LinearLayoutManager(container?.context)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        buttonDialog.setOnClickListener {
            this.showAddNoteBottomSheet()
        }

        return  view;
    }


    private fun showAddNoteBottomSheet() {
        val bottomSheet = AddNoteBottomSheetFragment().apply {
            setOnAddNoteListener(this@ShowNotesFragment)
        }
        bottomSheet.show(parentFragmentManager, "AddNoteBottomSheetTag")
    }

    override fun onNoteAdded(note: Notes) {
        notes.add(note)
        adapter.notifyDataSetChanged()
    }


    private fun initData() {

        notes = mutableListOf(
            Notes(1, "Escuela", "20/11/23", "Proyecto Souffle"),
            Notes(2, "Escuela", "20/11/23", "Proyecto Acuña"),
            Notes(3, "Mercado", "10/11/23", "Shampoo"),
            Notes(4, "Mercado", "10/11/23", "Acondicionador"),
            Notes(5, "Mercado", "10/11/23", "Anyol"),
            Notes(6, "Mercado", "10/11/23", "Pan"),
            Notes(7, "Mercado", "10/11/23", "Shampoo"),
            Notes(8, "Tarea", "8/11/23", "Adelina"),
            Notes(9, "Project", "8/11/23", "Adelina"),
            Notes(10, "Libro", "8/11/23", "El Psicoanalista Stephen King"),



        )

    }

}