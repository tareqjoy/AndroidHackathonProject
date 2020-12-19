package com.tigerit.androidhackathonproject.models

data class TrendingContents(
    val page: Int,
    val results: List<SingleTrendingContent>,
    val total_pages: Int,
    val total_results: Int
)