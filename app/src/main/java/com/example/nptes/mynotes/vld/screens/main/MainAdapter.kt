package com.example.nptes.mynotes.vld.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nptes.mynotes.vld.R
import com.example.nptes.mynotes.vld.models.AppNote
import kotlinx.android.synthetic.main.note_item.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    private var listNotes = emptyList<AppNote>()

    class MainHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameNote: TextView = view.findViewById(R.id.item_note_name)
        val textNote: TextView = view.findViewById(R.id.item_note_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.textNote.text = listNotes[position].text
        holder.nameNote.text = listNotes[position].name
    }

    override fun getItemCount(): Int = listNotes.size

    fun setList(list: List<AppNote>) {
        listNotes = list
        notifyDataSetChanged()
    }
}