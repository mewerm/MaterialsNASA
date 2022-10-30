package com.maximmesh.nasamaterials.ui.fragments.planets

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.maximmesh.nasamaterials.databinding.FragmentEarthBinding

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rotatePicture()
    }

    /**Здесь задействовал анимацию также. При нажатии на картинку,
    она вращается. Такое же реализовано и для остальных фрагментов из ViewPager*/
    private fun rotatePicture(){
        binding.earthImage.setOnClickListener {
            ObjectAnimator.ofFloat(binding.earthImage, View.ROTATION, 0f, 360f)
                .setDuration(2000L).start()
        }
    }
}

