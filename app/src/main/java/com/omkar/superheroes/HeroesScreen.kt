package com.omkar.superheroes

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omkar.superheroes.data.HeroesRepository
import com.omkar.superheroes.model.Hero

@Composable
fun SuperHeroesList(
    modifier: Modifier = Modifier,
    heroesList: List<Hero>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(contentPadding = contentPadding) {
        items(heroesList) {
            hero  ->  HeroCard(
                heroDetails = hero,
                modifier = modifier
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    )
            )
        }
    }
}


@Composable
fun HeroCard(
    heroDetails: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier.clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = heroDetails.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = heroDetails.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = modifier.width(16.dp))
            HeroIcon(imageRes = heroDetails.imageRes)
        }
    }
}

@Composable
fun HeroIcon(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(72.dp)
            .clip(MaterialTheme.shapes.small)
    ){
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun HeroCardPreview() {
    HeroCard(
        Hero(
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SuperheroesListPreview() {
    SuperHeroesList(heroesList = HeroesRepository.loadHeroes)
}