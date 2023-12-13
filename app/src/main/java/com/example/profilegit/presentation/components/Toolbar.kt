package com.example.profilegit.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.profilegit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        title = {
            Text(
                text = "",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,

                )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarDetail(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = "",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,

                )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarDetailPrev() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,

                )
        },
        navigationIcon = {
            IconButton(onClick = {
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
            }
        }
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    ToolbarDetailPrev()
}