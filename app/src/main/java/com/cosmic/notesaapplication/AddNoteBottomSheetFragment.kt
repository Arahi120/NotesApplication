package com.cosmic.notesaapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.UUID


class AddNoteBottomSheetFragment : BottomSheetDialogFragment()  {

    interface OnAddNoteListener {
        fun onNoteAdded(note: Notes)
    }

    private var listener: OnAddNoteListener? = null

    fun setOnAddNoteListener(listener: OnAddNoteListener) {
        this.listener = listener
    }

    fun generateUniqueDeviceID(): Int {
        // Generar un UUID (Identificador Único Universal) aleatorio
        val uuid = UUID.randomUUID()

        // Obtener un número único a partir del hash del UUID
        val number = uuid.hashCode()

        // Asegurarse de que el número sea siempre positivo
        return if (number < 0) - number else number
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val height = resources.displayMetrics.heightPixels

        // Establece una altura personalizada, por ejemplo, el 80% de la pantalla
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_note_bottom_sheet, container, false)

        val editTextTittle= view.findViewById<EditText>(R.id.editTextTitle)
        val editTextDate = view.findViewById<EditText>(R.id.editTextDate)
        val editTextContent = view.findViewById<EditText>(R.id.editTextContent)
        val buttonAddNote = view.findViewById<Button>(R.id.buttonAddNote)

        buttonAddNote.setOnClickListener {
            val ttle = editTextTittle.text.toString()
            val dt = editTextDate.text.toString()
            val contentt = editTextContent.text.toString()

            // Validar los datos y crear un objeto NetworkDevice
            if (ttle.isNotEmpty() && dt.isNotEmpty() && contentt.isNotEmpty()) {
                val newNote = Notes(
                    id = generateUniqueDeviceID(),
                    title = ttle,
                    date = dt,
                    content = contentt)

                // Llamar al método onDeviceAdded de la interfaz
                listener?.onNoteAdded(newNote)

                // Cerrar el diálogo
                dismiss()
            } else {
                // Mostrar un mensaje de error o manejar la validación de datos incompletos
            }
        }



        return view
    }

}