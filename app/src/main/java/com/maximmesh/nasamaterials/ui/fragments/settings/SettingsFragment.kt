package com.maximmesh.nasamaterials.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.maximmesh.nasamaterials.databinding.FragmentSettingsBinding
import com.maximmesh.nasamaterials.repository.ShredPrefSave

class SettingsFragment : Fragment() {

    private val THEME_BASE = 0
    private val THEME_GREEN = 1
    private val THEME_SAND = 2

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(
            ShredPrefSave(requireActivity().application)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        setThemeWhenClickButton()
    }

    private fun setThemeWhenClickButton() {
        with(binding) {
            themeBaseButton.setOnClickListener {
                settingsViewModel.setTheme(THEME_BASE)
                requireActivity().recreate()
            }
            themeGreenButton.setOnClickListener {
                settingsViewModel.setTheme(THEME_GREEN)
                requireActivity().recreate()
            }
            themeSandButton.setOnClickListener {
                settingsViewModel.setTheme(THEME_SAND)
                requireActivity().recreate()
            }
        }
    }

    private fun init() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            settingsViewModel.theme.collect {
                when (it) {
                    THEME_BASE -> {
                        binding.themeBaseButton.isChecked = true
                    }
                    THEME_GREEN -> {
                        binding.themeGreenButton.isChecked = true
                    }
                    THEME_SAND -> {
                        binding.themeSandButton.isChecked = true
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}