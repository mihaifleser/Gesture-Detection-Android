package com.example.bachelor_app.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bachelor_app.R
import com.example.bachelor_app.ui.model.ISensorsViewModel

@Composable
fun SensorsScreen(viewModel: ISensorsViewModel) {

    Surface {
        Column(Modifier.padding(dimensionResource(id = R.dimen.medium_padding))) {
            Text(
                text = stringResource(id = R.string.song),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.medium_padding))
            )
            Text(
                text = viewModel.currentSong.value,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.medium_padding))
            )
            Text(
                text = viewModel.tracker.value,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.medium_padding))
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = viewModel::previous) {
                    Icon(
                        Icons.Filled.SkipPrevious,
                        contentDescription = "Previous",
                    )
                }
                Button(onClick = viewModel::stopResume) {
                    Icon(
                        if (viewModel.play.value) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = "Resume",
                    )
                }
                Button(onClick = viewModel::next) {
                    Icon(
                        Icons.Filled.SkipNext,
                        contentDescription = "Previous",
                    )
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                viewModel.gestures.value.forEach {
                    item {
                        Text(
                            text = it, fontSize = 16.sp, modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}