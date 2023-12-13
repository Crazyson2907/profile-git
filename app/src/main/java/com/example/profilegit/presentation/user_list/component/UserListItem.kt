package com.example.profilegit.presentation.user_list.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.profilegit.domain.model.User
import com.example.profilegit.ui.theme.AppTheme

@Composable
fun UserListItem(
    item: User,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .background(color = AppTheme.colors.colorSecondary),
        shape = RoundedCornerShape(0.dp),
    ) {
        Surface() {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                // horizontalArrangement = Arrangement.Center
            ) {
                ProfilePic(item.avatar_url)
                ProfileDetail(user = item)
            }
        }
    }


}

@Composable
fun ProfileDetail(user: User) {
    Column(
        verticalArrangement = Arrangement.aligned(Alignment.CenterVertically),
        modifier = Modifier
            .padding(6.dp)
            .fillMaxHeight()

    ) {
        Text(
            text = user.login,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Clip

        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePic(avatarUrl: String) {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, color = AppTheme.colors.colorSecondary),
        modifier = Modifier
            .size(84.dp)
            // .padding(8.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp))),

        ) {
        GlideImage(
            model = avatarUrl,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(84.dp)
            // .padding(8.dp)
            // .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            ,
            contentScale = ContentScale.Crop
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UserItemPreview(user: User) {
    Card(
        modifier = Modifier
            // .wrapContentSize()
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color = AppTheme.colors.colorSecondary),
        shape = RoundedCornerShape(0.dp),
    ) {
        Row {
            ProfilePic(avatarUrl = user.avatar_url)
            ProfileDetail(user = user)
        }

    }

}