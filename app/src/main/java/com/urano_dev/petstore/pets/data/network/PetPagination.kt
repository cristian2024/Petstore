package com.urano_dev.petstore.pets.data.network

import com.google.gson.annotations.SerializedName
import com.urano_dev.petstore.common.data.Pagination
import com.urano_dev.petstore.common.domain.PaginationState
import com.urano_dev.petstore.pets.data.models.Animal

data class PetPagination(
    @SerializedName("pagination")
    override val pagination: Pagination,
    @SerializedName("animals")
    override val data: List<Animal>,
): PaginationState<List<Animal>>
