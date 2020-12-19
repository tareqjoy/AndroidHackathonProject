package com.tigerit.androidhackathonproject.models

data class Series(
    val page: Int,
    val results: List<SingleSeries>,
    val total_pages: Int,
    val total_results: Int
)