package com.example.networkingapp.data

data class Item(
    val id: Long?,
    val name: String?,
    val fullName: String?,
    val owner: Owner,
    val private: Boolean,
    val htmlUrl: String?,
    val description: String?)
