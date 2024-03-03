package com.urano_dev.petstore.pets.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urano_dev.petstore.common.data.Pagination
import com.urano_dev.petstore.common.domain.Status
import com.urano_dev.petstore.pets.data.models.Animal
import com.urano_dev.petstore.pets.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetsViewModel
@Inject constructor(
    private val petRepository: PetRepository,
) : ViewModel() {

    private val _pets = MutableStateFlow<List<Animal>>(listOf())
    val pets = _pets.asStateFlow()

    private val _pagination = MutableStateFlow(Pagination())
    val pagination = _pagination.asStateFlow()

    private val _status = MutableStateFlow(Status.NOT_STARTED)
    val status = _status.asStateFlow()

    init {
        getNextPets()
    }

    fun getNextPets() {
        viewModelScope.launch {
            try {
                _status.value = Status.LOADING
                val paginationData = pagination.value
                val dataResponse = petRepository.getAnimals(
                    paginationData = paginationData.copy(
                        currentPage = paginationData.currentPage + 1,
                    )
                )
                _pets.value += dataResponse.data
                _pagination.value = dataResponse.pagination
                _status.value = Status.FINISHED
            } catch (e: Exception) {
                _status.value = Status.ERROR
                print(e)
            }
        }
    }

}