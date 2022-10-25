package com.maximmesh.nasamaterials.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maximmesh.nasamaterials.repository.ShredPrefSave
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val themeStorage: ShredPrefSave
) : ViewModel() {

    private val _theme = MutableStateFlow<Int>(0)
    val theme: Flow<Int> = _theme

    init {
        viewModelScope.launch {
            _theme.value = themeStorage.themeID
        }
    }

    fun setTheme(themeID: Int) {
        themeStorage.themeID = themeID
        _theme.value = themeID
    }

}