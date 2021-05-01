package com.codangcoding.ilt10.data.network.dto

data class Pagination<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)