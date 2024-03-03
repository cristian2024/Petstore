package com.urano_dev.petstore.pets.ui.components

import androidx.compose.runtime.Composable
import com.urano_dev.petstore.common.ui.components.InfiniteScrollList
import com.urano_dev.petstore.pets.data.models.Animal


@Composable
fun PetsList(
    pets: List<Animal>,
    loadMore: () -> Unit,
) {
    InfiniteScrollList(
        listItems = pets,
        loadMore = loadMore,
    ){pet->
        PetCard(pet = pet)
    }
}