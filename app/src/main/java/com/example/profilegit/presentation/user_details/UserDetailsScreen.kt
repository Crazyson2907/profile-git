package com.example.profilegit.presentation.user_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.profilegit.domain.core.model.Details
import com.example.profilegit.presentation.components.CircularProgress
import com.example.profilegit.presentation.components.ToolbarDetail
import com.example.profilegit.ui.theme.AppTheme

@Composable
fun UserDetailsScreen(
    navController: NavHostController,
    viewModel: UserDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is UserDetailState.Loading -> CircularProgress()
        is UserDetailState.DetailsSuccessfullyFetched -> Profile(navController, currentState.user)
        is UserDetailState.ErrorOccurred -> Text("Error: ${currentState}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController, user: Details) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarDetail(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            ProfilePic(avatarUrl = user.avatarUrl)
            ProfileDetail(user = user)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePic(avatarUrl: String) {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, color = AppTheme.colors.colorSecondary),
        modifier = Modifier
            .size(184.dp)
            .clip(RoundedCornerShape(corner = CornerSize(1.dp))),

        ) {
        GlideImage(
            model = avatarUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(184.dp)
                .clip(RoundedCornerShape(corner = CornerSize(1.dp))),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileDetail(user: Details) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxHeight()

    ) {
        Text(
            text = user.login,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = user.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
        user.location?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
    }
}