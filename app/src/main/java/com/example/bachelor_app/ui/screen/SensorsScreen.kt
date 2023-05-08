package com.example.bachelor_app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.bachelor_app.R
import com.example.bachelor_app.ui.model.ISensorsViewModel

@Composable
fun SensorsScreen(viewModel: ISensorsViewModel) {

    Surface(modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))) {
        Column {
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
        }
    }
}