package com.example.profilegit.presentation.user_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.profilegit.presentation.Screen
import com.example.profilegit.presentation.components.Toolbar
import com.example.profilegit.presentation.user_list.component.UserListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Toolbar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(state.users) { user ->
                UserListItem(
                    item = user,
                    onItemClick = {
                        navController.navigate(Screen.UserDetailsScreen.route + "/${user.login}")
                    }
                )
            }
        }
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}