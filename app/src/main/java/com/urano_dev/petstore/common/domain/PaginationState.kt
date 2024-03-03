package com.urano_dev.petstore.common.domain

import com.urano_dev.petstore.common.data.Pagination

interface  PaginationState<T>{
    val pagination: Pagination
    val data: T
}