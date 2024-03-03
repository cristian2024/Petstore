package com.urano_dev.petstore.theme.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urano_dev.petstore.theme.data.repository.ThemePreferencesRepository
import com.urano_dev.petstore.theme.domain.ThemeType
import com.urano_dev.petstore.theme.domain.getNextTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeChangeViewModel @Inject constructor(
    private val themeRepository: ThemePreferencesRepository,
) : ViewModel() {

    init {
        loadInitialTheme()
    }

    fun loadInitialTheme() {
        viewModelScope.launch {
            val type = themeRepository.readValue()
            if (type != null)
                _themeType.value = type

        }
    }

    fun changeTheme(type: ThemeType) {

        viewModelScope.launch {
            themeRepository.setValue(type.name.lowercase())
        }
        _themeType.value = type
    }

    fun toggleTheme() {
        val newType = themeType.value.getNextTheme()
        viewModelScope.launch {

            themeRepository.setValue(
                newType.name.lowercase(),
            )
        }
        _themeType.value = newType
    }

    private val _themeType = MutableStateFlow(ThemeType.DEFAULT)
    val themeType: StateFlow<ThemeType> = _themeType.asStateFlow()
}