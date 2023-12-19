package com.example.profilegit.presentation.user_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.profilegit.domain.core.model.Category
import com.example.profilegit.domain.core.model.User
import com.example.profilegit.presentation.Screen
import com.example.profilegit.presentation.components.CircularProgress
import com.example.profilegit.presentation.components.Toolbar
import com.example.profilegit.presentation.user_list.component.CategoryHeader
import com.example.profilegit.presentation.user_list.component.UserListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navController: NavHostController,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    var showCategorizedList by remember { mutableStateOf(false) }
    var isClicked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Toolbar(isClicked = isClicked,
                onIconButtonClicked = {
                    showCategorizedList = !showCategorizedList
                    isClicked = !isClicked
                })
        }
    ) { innerPadding ->
        when (val currentState = state.value) {
            is UserListState.Loading -> CircularProgress()
            is UserListState.ListSuccessfullyFetched -> {
                if (showCategorizedList) {
                    val categories = currentState.list
                        .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.login })
                        .groupBy { it.login.first().toString() }
                        .map { Category(name = it.key, items = it.value) }
                    CategorizedList(categories, navController, innerPadding, viewModel)
                } else {
                    UserList(navController, currentState.list, innerPadding, viewModel)
                }
            }

            is UserListState.ErrorOccurred -> Text("Error: $currentState")
            else -> {}
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserList(
    navController: NavController,
    users: List<User>,
    innerPadding: PaddingValues,
    viewModel: UserListViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(users) { user ->
                UserListItem(
                    item = user,
                    onItemClick = {
                        navController.navigate(Screen.UserDetailsScreen.route + "/${user.login}")
                    },
                    isFavorite = user.isFavorite,
                    onFavoriteClick = {
                        viewModel.toggleFavorite(user)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun CategorizedList(
    categories: List<Category>,
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: UserListViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            categories.forEach { category ->
                stickyHeader {
                    CategoryHeader(text = category.name)
                }
                items(category.items) { user ->
                    UserListItem(item = user, onItemClick = {
                        navController.navigate(Screen.UserDetailsScreen.route + "/${it.login}")
                    },
                        isFavorite = user.isFavorite,
                        onFavoriteClick = {
                            viewModel.toggleFavorite(user)
                        })
                }
            }
        }
    }
}