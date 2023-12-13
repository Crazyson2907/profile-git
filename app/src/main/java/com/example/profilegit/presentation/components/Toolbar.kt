package com.example.profilegit.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
                text = "Користувачі Github",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon()
            }
        }
    )
}

@Composable
fun Icon() {
    val painter = painterResource(id = R.drawable.outline_filter_alt_24)
    Image(
        painter = painter,
        contentDescription = "My Vector Icon"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarDetail(navController: NavHostController) {
    TopAppBar(
        title = {
            Text(
                text = "Деталі користувача",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go Back",
                    tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.StarOutline,
                    contentDescription = "Add to favorites",
                    tint = Color.Yellow)
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