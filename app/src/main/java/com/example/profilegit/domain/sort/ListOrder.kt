package com.example.profilegit.domain.sort

sealed class ListOrder(val orderType: OrderType) {

    class Login(orderType: OrderType) : ListOrder(orderType)

    fun copy(orderType: OrderType): ListOrder {
        return when (this) {
            is Login -> Login(orderType)
        }
    }
}
