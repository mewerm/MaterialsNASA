package com.maximmesh.nasamaterials.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maximmesh.nasamaterials.repository.ShredPrefSave

class SettingsViewModelFactory(
    private val themeStorage: ShredPrefSave
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        SettingsViewModel(themeStorage) as T
}