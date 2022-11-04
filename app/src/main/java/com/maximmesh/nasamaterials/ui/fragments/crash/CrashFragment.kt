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
import com.maximmesh.nasamaterials.utils.CRASH_ITEM_COUNT
import com.maximmesh.nasamaterials.utils.DURATION_FOR_ITEMS_CRASH
import com.maximmesh.nasamaterials.utils.DURATION_FOR_SCREEN

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

        /**
         * Здесь реализовал анимацию, при нажатии на любой item от него отчуждаются другие и он в том числе
         * со скоростью DURATION_FOR_ITEMS_CRASH. Далее наступает эффект "GAME OVER" - фэйд затухания экрана
         * со скоростью DURATION_FOR_SCREEN
         * с последующей индикацией о "успешности завхвата мира" в виде сообщения TOAST посередине экрана за счет
         * Gravity.CENTER_HORIZONTAL
         */
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val rect = Rect()
                it.getGlobalVisibleRect(rect)
                val explode = Explode()
                explode.duration = DURATION_FOR_ITEMS_CRASH
                explode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return rect
                    }
                }
                TransitionManager.beginDelayedTransition(binding.recyclerView, explode)
                binding.recyclerView.animate().alpha(0f).setDuration(DURATION_FOR_SCREEN).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            Toast.makeText(context, "THE WORLD IS INVADED", Toast.LENGTH_LONG)
                                .apply {
                                    setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
                                    show()
                                }
                        }
                    }
                )
                binding.recyclerView.adapter = null
            }
        }

        override fun getItemCount() = CRASH_ITEM_COUNT

        inner class MyViewHolder(binding: ItemForCrashBinding) :
            RecyclerView.ViewHolder(binding.root)
    }
}