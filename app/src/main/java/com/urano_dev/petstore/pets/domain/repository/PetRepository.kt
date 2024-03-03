package com.urano_dev.petstore.pets.domain.repository

import com.urano_dev.petstore.common.data.Pagination
import com.urano_dev.petstore.pets.data.network.PetPagination

interface PetRepository {

    suspend fun getAnimals(paginationData: Pagination): PetPagination
}