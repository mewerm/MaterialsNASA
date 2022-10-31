package com.maximmesh.nasamaterials.ui.fragments.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.maximmesh.nasamaterials.databinding.FragmentNotesBinding
import com.maximmesh.nasamaterials.repository.notes.NoteData

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val data = arrayListOf(
        NoteData(1)
    )
    private val callbackAdd = AddItem {
        data.add(
            it, NoteData(
                1, "We know u need NASA picture app",
                "so we put a wiki search and note-taking feature  " +
                        "in nasa app\nso u can write note while look picture " +
                        "while using search wiki while planets spin"
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