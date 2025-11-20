package com.example.heroesofmightandmagiciii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import com.example.heroesofmightandmagiciii.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.heroesofmightandmagiciii.data.DataSourse
import com.example.heroesofmightandmagiciii.model.Topic
import com.example.heroesofmightandmagiciii.ui.theme.HeroesOfMightAndMagicIIITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeroesOfMightAndMagicIIITheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Black)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Кунц-камера",
                        color = White,
                        modifier = Modifier.padding(top = 26.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    val layoutDirection = LocalLayoutDirection.current

    Surface (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ){}
    AffirmationList(
        affirmationList = DataSourse().loadTopic(),
    )

}

@Composable
fun AffirmationList(affirmationList: List<Topic>, modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Topic, modifier: Modifier = Modifier) {

    Card(modifier = modifier) {
        Row (
            modifier = modifier
                .height(68.dp)
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .padding(8.dp),
                fontSize = 10.sp,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    Box(
        modifier = Modifier
            .padding(top = 50.dp)
    ) {

    }
    AffirmationCard(Topic(R.string.a1, R.drawable.__2025_11_20__14_54_40))

}
