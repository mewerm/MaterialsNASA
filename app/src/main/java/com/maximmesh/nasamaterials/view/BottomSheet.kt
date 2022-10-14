package com.maximmesh.nasamaterials.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maximmesh.nasamaterials.R
import com.maximmesh.nasamaterials.databinding.BottomSheetLayoutBinding
import com.maximmesh.nasamaterials.utils.toast
import com.maximmesh.nasamaterials.view.PictureOfTheDayFragment.Companion.newInstance
import com.maximmesh.nasamaterials.viewmodel.AppState
import com.maximmesh.nasamaterials.viewmodel.PictureOfTheDayViewModel

class BottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = { data: AppState -> renderData(data) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
    }


    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val text = data.serverResponseData
                binding.bottomSheetDescriptionHeader.text = text.title
                binding.bottomSheetDescription.text = text.explanation

            }
            is AppState.Loading -> {
                //TODO
            }
            is AppState.Error -> {
                toast(data.error.message)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = BottomSheet()
    }
}