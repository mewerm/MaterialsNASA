package com.maximmesh.nasamaterials.ui.fragments.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.maximmesh.nasamaterials.databinding.FragmentNotesBinding
import com.maximmesh.nasamaterials.repository.notes.NoteData
import com.maximmesh.nasamaterials.utils.DESCRIPTION_WHEN_U_RESS_PLUS
import com.maximmesh.nasamaterials.utils.HEAD_TEXT_WHEN_U_PRESS_PLUS

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val data = arrayListOf(
        NoteData(1)
    )
    private val callbackAdd = AddItem {
        data.add(
            it, NoteData(
                1, HEAD_TEXT_WHEN_U_PRESS_PLUS, DESCRIPTION_WHEN_U_RESS_PLUS
            )
        )
        adapter.setListDataAdd(data, it)
    }
    private val callbackRemove = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data, it)
    }
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NotesAdapter(data, callbackAdd, callbackRemove)
        binding.recyclerView.adapter = adapter
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}