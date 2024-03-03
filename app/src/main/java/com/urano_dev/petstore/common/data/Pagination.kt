package com.urano_dev.petstore.common.data

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("count_per_page")
    val countPerPage: Int = 0,
    @SerializedName("total_count")
    val totalCount: Int = 0,
    @SerializedName("current_page")
    val currentPage: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
)

//"pagination": {
//    "count_per_page": 20,
//    "total_count": 320,
//    "current_page": 1,
//    "total_pages": 16,
//    "_links": {}
//}