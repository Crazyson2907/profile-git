package com.example.profilegit.domain.sort

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}
