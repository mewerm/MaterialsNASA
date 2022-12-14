package com.maximmesh.nasamaterials.ui.fragments.planets

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maximmesh.nasamaterials.databinding.FragmentMarsBinding
import com.maximmesh.nasamaterials.utils.DURATION_FOR_MARS

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rotatePicture()
    }

    /**Здесь задействовал анимацию также. При нажатии на картинку,
    она вращается. Такое же реализовано и для остальных фрагментов из ViewPager*/
    private fun rotatePicture(){
        binding.marsPicture.setOnClickListener {
            ObjectAnimator.ofFloat(binding.marsPicture, View.ROTATION, 0f, 360f)
                .setDuration(DURATION_FOR_MARS).start()
        }
    }
}

