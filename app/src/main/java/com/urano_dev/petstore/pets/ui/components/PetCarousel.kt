@file:OptIn(ExperimentalPagerApi::class)

package com.urano_dev.petstore.pets.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.urano_dev.petstore.R
import com.urano_dev.petstore.pets.domain.PetPhotos
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun PetCarousel(petPhotos: List<PetPhotos>) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(80.dp)
            .height(80.dp),
    ) {
        if (petPhotos.isNotEmpty()) {
            Carousel(petPhotos, Modifier)
        } else {

            val petImageInt = R.drawable.dog_no_pet
            val image = painterResource(petImageInt)
            Image(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                painter = image,
                contentDescription = "Pet image"
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
private fun Carousel(petPhotos: List<PetPhotos>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(initialPage = 0)


    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(5000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column {
        HorizontalPager(
            count = petPhotos.size,
            state = pagerState,
        ) { page ->
            val pet = petPhotos[page]
            AsyncImage(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                model = pet.large,
                contentDescription = "large photo",

            )
        }

    }
}