package com.maximmesh.nasamaterials.ui.fragments.crash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.maximmesh.nasamaterials.databinding.FragmentCrashBinding
import com.maximmesh.nasamaterials.databinding.ItemForCrashBinding

class CrashFragment : Fragment() {

    private var _binding: FragmentCrashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = RecyclerAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class RecyclerAdapter
        : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val binding = ItemForCrashBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return MyViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val rect = Rect()
                it.getGlobalVisibleRect(rect)
                val explode = Explode()
                explode.duration = 4000L
                explode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return rect
                    }
                }
                TransitionManager.beginDelayedTransition(binding.recyclerView, explode)
                binding.recyclerView.animate().alpha(0f).setDuration(5000L).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            Toast.makeText(context, "THE WORLD IS INVADED", Toast.LENGTH_LONG).apply {
                                setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                                show()
                            }
                        }
                    }
                )
                binding.recyclerView.adapter = null
            }
        }

        override fun getItemCount() = 100500

        inner class MyViewHolder(private val binding: ItemForCrashBinding) :
            RecyclerView.ViewHolder(binding.root)
    }
}