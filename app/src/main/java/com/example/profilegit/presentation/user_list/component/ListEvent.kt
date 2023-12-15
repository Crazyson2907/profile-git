package com.example.profilegit.presentation.user_list.component

import com.example.profilegit.domain.sort.ListOrder

sealed class ListEvent {

    data class Order(val listOrder: ListOrder): ListEvent()
}
