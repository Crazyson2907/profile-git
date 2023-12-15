package com.example.profilegit.data.dto

import com.example.profilegit.domain.core.model.Details

data class DetailsDto(
    val avatar_url: String,
    val bio: Any? = null,
    val blog: String,
    val company: String,
    val created_at: String,
    val email: Any? = null,
    val events_url: String,
    val followers: Int = 0,
    val followers_url: String,
    val following: Int = 0,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val hireable: Any? = null,
    val html_url: String,
    val id: Int = 0,
    val location: String,
    val login: String,
    val name: String,
    val node_id: String,
    val organizations_url: String,
    val public_gists: Int = 0,
    val public_repos: Int = 0,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean = false,
    val starred_url: String,
    val subscriptions_url: String,
    val twitter_username: String,
    val type: String,
    val updated_at: String,
    val url: String
)

fun DetailsDto.toDetails(): Details {
    return Details(
        id = id,
        avatarUrl = avatar_url,
        login = login,
        name = name,
        location = location
    )
}
