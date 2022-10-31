package com.maximmesh.nasamaterials.ui.fragments.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maximmesh.nasamaterials.R
import com.maximmesh.nasamaterials.databinding.FragmentNotesItemBinding
import com.maximmesh.nasamaterials.repository.notes.NoteData

class NotesAdapter(
    private var listData: MutableList<NoteData>,
    val callbackAddItem: AddItem,
    private val callbackRemove: RemoveItem

) : ListAdapter<NoteData, NotesAdapter.Holder>(Comparator()), ItemTouchHelperAdapter {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = FragmentNotesItemBinding.bind(view)

        fun bind(noteData: NoteData) = with(binding) {
            binding.title.setText(noteData.name)
            binding.message.setText(noteData.someDescription)
            binding.addItemImageView.setOnClickListener {
                callbackAddItem.add(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_notes_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listData[position])

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        callbackRemove.remove(position)
    }

    fun setListDataRemove(listNoteDataNew: MutableList<NoteData>, position: Int) {
        listData = listNoteDataNew
        notifyItemRemoved(position)
    }

    fun setListDataAdd(listNoteDataNew: MutableList<NoteData>, position: Int) {
        listData = listNoteDataNew
        notifyItemInserted(position)
    }

    class Comparator :
        DiffUtil.ItemCallback<NoteData>() {
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean {
            return oldItem == newItem
        }
    }
}
