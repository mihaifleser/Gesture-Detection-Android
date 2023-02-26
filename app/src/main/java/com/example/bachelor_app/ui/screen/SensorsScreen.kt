package com.example.bachelor_app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bachelor_app.R
import com.example.bachelor_app.ui.theme.BachelorAppTheme

@Composable
fun SensorsScreen() {

    Surface(modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))) {
        Column {
            Text(
                text = stringResource(id = R.string.sensors),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.medium_padding))
            )
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.connect),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SensorsPreview() {
    BachelorAppTheme {
        SensorsScreen()
    }
}