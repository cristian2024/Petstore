package com.urano_dev.petstore.pets.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.urano_dev.petstore.pets.data.models.AnimalGender
import java.util.Locale

@Composable
fun PetGenderChip(
    petGender: AnimalGender,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(

            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = petGender.toString()
                    .lowercase()
                    .capitalize(Locale.ROOT),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,

                modifier = modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp,
                    )
            )

        }
    }

}