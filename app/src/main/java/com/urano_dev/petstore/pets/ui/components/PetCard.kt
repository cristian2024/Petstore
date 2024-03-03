package com.urano_dev.petstore.pets.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.urano_dev.petstore.pets.data.models.Animal
import com.urano_dev.petstore.pets.data.models.getAddress
import com.urano_dev.petstore.pets.data.models.getCharacteristics
import java.util.Locale


@Composable
fun PetCard(pet: Animal) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                println("hello")
            },
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .height(IntrinsicSize.Min)
        ) {
            PetCarousel(pet.photos)
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(2f),
            ) {
                Text(
                    text = pet.name,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(text = pet.getCharacteristics())
                Row{
                    Icon(  Icons.Default.LocationOn, contentDescription = "")
                    Text(text = pet.getAddress())
                }

            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,

                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                PetGenderChip(
                    pet.gender,
                )
                Column {
                    Text(
                        text = pet.status.name.lowercase().capitalize(Locale.ROOT),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

}