package com.example.notes.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemNoteBinding
import com.example.notes.model.AppNote

class Adapter: RecyclerView.Adapter<Adapter.MyHolder>() {

    private var listNotes = emptyList<AppNote>()

    fun setList(list: List<AppNote>){
        listNotes = list
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return  listNotes.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val note = listNotes[position]

        holder.binding.itemNoteName.text = note.name
        holder.binding.itemNoteText.text = note.text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return MyHolder(binding)
    }

    class MyHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root){
        val nameNote: TextView = binding.itemNoteName
        val textNote: TextView = binding.itemNoteText
    }


    override fun onViewAttachedToWindow(holder: MyHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            FragmentMain.click(listNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MyHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }


}