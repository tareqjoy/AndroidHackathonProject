package com.tigerit.androidhackathonproject.models

data class Movies(
    val page: Int,
    val results: List<SingleMovie>,
    val total_pages: Int,
    val total_results: Int
)