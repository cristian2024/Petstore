package com.urano_dev.petstore.pets.data.repository

import com.urano_dev.petstore.common.data.Pagination
import com.urano_dev.petstore.pets.data.network.PetPagination
import com.urano_dev.petstore.pets.data.network.api.PetFinderApi
import com.urano_dev.petstore.pets.domain.repository.PetRepository

class PetApiRepository(private val petApi: PetFinderApi) : PetRepository {
    override suspend fun getAnimals(paginationData: Pagination): PetPagination {
        try {
            return petApi.getAnimals(
                paginationData.currentPage,
            )
        } catch (error: Exception) {
            print(error)
            throw error
        }

    }
}