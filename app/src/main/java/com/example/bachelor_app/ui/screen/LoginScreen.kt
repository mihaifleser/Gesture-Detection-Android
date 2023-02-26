package com.example.bachelor_app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bachelor_app.R
import com.example.bachelor_app.ui.theme.BachelorAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onSignUp: (String, String) -> Unit, onLogIn: (String, String) -> Unit) {

    Surface(modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_padding))) {
        Column {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Text(
                text = stringResource(id = R.string.welcome),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 30.sp
                )
            )

            Text(text = stringResource(id = R.string.email))
            TextField(
                onValueChange = { username = it },
                value = username,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(text = stringResource(id = R.string.password))
            TextField(
                onValueChange = { password = it },
                value = password,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.medium_padding)),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.medium_padding))
            ) {
                Button(
                    onClick = { onSignUp.invoke(username, password) },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))
                    )
                }
                Button(
                    onClick = { onLogIn.invoke(username, password) },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding))
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    BachelorAppTheme {
        LoginScreen({ _, _ -> {} }, { _, _ -> {} })
    }
}