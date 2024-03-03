package com.urano_dev.petstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.urano_dev.petstore.common.domain.isLoading
import com.urano_dev.petstore.pets.ui.components.PetsList
import com.urano_dev.petstore.pets.viewModel.PetsViewModel
import com.urano_dev.petstore.theme.ui.PetStoreTheme
import com.urano_dev.petstore.theme.ui.ThemeToggler
import com.urano_dev.petstore.theme.viewModel.ThemeChangeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel = hiltViewModel<ThemeChangeViewModel>()
            val petsViewModel = hiltViewModel<PetsViewModel>()


            val theme = themeViewModel.themeType
            PetStoreTheme(theme) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier.padding(
                            horizontal = 32.dp
                        )
                    ) {
                        ThemeToggler(themeViewModel::toggleTheme)
                        PetsEncapsuler(petsViewModel)
                    }
                }
            }
        }

    }


}


@Composable
fun PetsEncapsuler(petsViewModel: PetsViewModel) {
    val status by petsViewModel.status.collectAsState()
    val pets by petsViewModel.pets.collectAsState()

    PetsList(
        pets = pets,
    ) {
        if (!status.isLoading())
            petsViewModel.getNextPets()
    }
//    if (status.isLoading())
    CircularProgressIndicator()


}